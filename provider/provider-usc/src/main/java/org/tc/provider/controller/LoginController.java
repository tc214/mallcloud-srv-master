package org.tc.provider.controller;

import com.google.common.base.Preconditions;
import org.tc.base.dto.UserTokenDto;
import org.tc.base.util.RequestUtils;
import org.tc.wrapper.WrapMapper;
import org.tc.wrapper.Wrapper;
import org.tc.provider.enums.UserTokenStatusEnum;
import org.tc.provider.model.dto.UserLoginDto;
import org.tc.provider.service.UserService;
import org.tc.provider.service.UserTokenService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/usc")
public class LoginController extends BaseController {
    @Resource
    private UserService uscUserService;

    @Resource
    private UserTokenService userTokenService;
    @Resource
    private ClientDetailsService clientDetailsService;

    private static final String BEARER_TOKEN_TYPE = "Basic ";


    @RequestMapping("/test")
    public String index() {
        String services = "[LoginController: test]";
        return services;
    }

    /**
     * 用户登录.
     *
     * @param user the user
     * @return the wrapper
     */
    @PostMapping("/aauth/form/test")
    @ApiOperation(httpMethod = "POST", value = "用户登录")
    public Wrapper loginTest(@RequestBody UserLoginDto user) {
        System.out.println("login-start");
        String res = uscUserService.login(user);
        if (!"".equals(res)) {
            return WrapMapper.error(res);
        }

        return WrapMapper.ok();
    }

//    @PostMapping("/auth/form")
//    @ApiOperation(httpMethod = "POST", value = "用户登录")
//    public Wrapper login(@RequestBody UserLoginDto user) {
//        System.out.println("login-start");
////        String deviceId = request.getHeader("deviceId");
//        String res = uscUserService.login(user);
//        if (!"".equals(res)) {
//            return WrapMapper.error(res);
//        }
//
//        return WrapMapper.ok();
//    }


    @GetMapping(value = "/auth/user/refreshToken")
    @ApiOperation(httpMethod = "POST", value = "刷新token")
    public Wrapper<String> refreshToken(HttpServletRequest request, @RequestParam(value = "refreshToken") String refreshToken, @RequestParam(value = "accessToken") String accessToken) {
        String token;
        try {
            Preconditions.checkArgument(org.apache.commons.lang3.StringUtils.isNotEmpty(accessToken), "accessToken is null");
            Preconditions.checkArgument(org.apache.commons.lang3.StringUtils.isNotEmpty(refreshToken), "refreshToken is null");
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
            } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
                throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
            }

            token = userTokenService.refreshToken(accessToken, refreshToken, request);
        } catch (Exception e) {
//            logger.error("refreshToken={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.ok(token);
    }

    @PostMapping(value = "/user/logout")
    @ApiOperation(httpMethod = "POST", value = "登出")
    public Wrapper logOut(String accessToken) {
        if (!StringUtils.isEmpty(accessToken)) {
            // 修改用户在线状态
            UserTokenDto userTokenDto = userTokenService.getByAccessToken(accessToken);
            userTokenDto.setStatus(UserTokenStatusEnum.OFF_LINE.getStatus());
            userTokenService.updateUserToken(userTokenDto, getLoginAuthDto());
        }
        return WrapMapper.ok();
    }


}
