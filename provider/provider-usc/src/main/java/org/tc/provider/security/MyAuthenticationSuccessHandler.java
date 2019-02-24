package org.tc.provider.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.tc.base.util.RequestUtils;
import org.tc.wrapper.WrapMapper;
import org.tc.util.StringUtil;
import org.tc.provider.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 认证成功处理器.
 */
@Component("myAuthenticationSuccessHandler")
@Slf4j
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private ClientDetailsService clientDetailsService;
    @Resource
    private UserService userService;
    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    private static final String BEARER_TOKEN_TYPE = "Basic ";


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功");
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }

        String[] tokens = RequestUtils.extractAndDecodeHeader(header);
        assert tokens.length == 2;

        String clientId = tokens[0];
        String clientSecret = tokens[1];

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
        } else if (!StringUtil.equals(clientDetails.getClientSecret(), clientSecret)) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
        }

        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        SecurityUser principal = null;
        try {
            principal = (SecurityUser) authentication.getPrincipal();
        } catch (Exception eee) {
            eee.printStackTrace();
        }
        userService.handlerLoginData(token, principal, request);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write((objectMapper.writeValueAsString(WrapMapper.ok(token))));
    }

}
