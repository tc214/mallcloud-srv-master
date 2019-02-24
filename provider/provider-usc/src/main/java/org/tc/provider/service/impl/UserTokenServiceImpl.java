package org.tc.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.tc.base.dto.LoginAuthDto;
import org.tc.base.dto.UserTokenDto;
import org.tc.base.util.RequestUtils;
import org.tc.provider.mapper.UserTokenMapper;
import org.tc.util.PublicUtil;
import org.tc.util.RedisKeyUtil;
import org.tc.core.support.BaseService;
import org.tc.properties.OAuth2ClientProperties;
import org.tc.properties.SecurityProperties;
import org.tc.provider.enums.UserTokenStatusEnum;
import org.tc.provider.model.domain.UserInfo;
import org.tc.provider.model.domain.UserToken;
import org.tc.provider.model.dto.token.TokenMainQueryDto;
import org.tc.provider.service.UserService;
import org.tc.provider.service.UserTokenService;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
@Transactional(rollbackFor = Exception.class)
public class UserTokenServiceImpl extends BaseService<UserToken> implements UserTokenService {
    @Resource
    private UserTokenMapper userUserTokenMapper;
    @Resource
    private UserService userService;
    @Autowired
    private SecurityProperties securityProperties;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Value("${mallcloud.auth.refresh-token-url}")
    private String refreshTokenUrl;


    @Override
    public void saveUserToken(String accessToken, String refreshToken, LoginAuthDto loginAuthDto,
                              HttpServletRequest request) {
        Long userId = loginAuthDto.getUserId();
        UserInfo userInfo = userService.selectByKey(userId);
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        final String os = userAgent.getOperatingSystem().getName();
        final String browser = userAgent.getBrowser().getName();
        final String remoteAddr = RequestUtils.getRemoteAddr(request);
        final String remoteLocation = "";

        // 存入mysql数据库
        UserToken userToken = new UserToken();
        OAuth2ClientProperties[] clients = securityProperties.getOauth2().getClients();
        int accessTokenValidateSeconds = clients[0].getAccessTokenValidateSeconds();
        int refreshTokenValiditySeconds = clients[0].getRefreshTokenValiditySeconds();
        userToken.setOs(os);
        userToken.setBrowser(browser);
        userToken.setAccessToken(accessToken);
        userToken.setAccessTokenValidity(accessTokenValidateSeconds);
        userToken.setLoginIp(remoteAddr);
        userToken.setLoginLocation(remoteLocation);
//		userToken.setLoginTime(userInfo.getLastLoginTime());
        userToken.setLoginName(loginAuthDto.getLoginName());
        userToken.setRefreshToken(refreshToken);
        userToken.setRefreshTokenValidity(refreshTokenValiditySeconds);
        userToken.setStatus(UserTokenStatusEnum.ON_LINE.getStatus());
        userToken.setUserId(userId);
        userToken.setUserName(loginAuthDto.getUserName());
        userToken.setUpdateInfo(loginAuthDto);
        userToken.setGroupId(loginAuthDto.getGroupId());
        userToken.setGroupName(loginAuthDto.getGroupName());
        userToken.setId(generateId());
//		userUserTokenMapper.insertSelective(userToken); // todo
        UserTokenDto userTokenDto = new ModelMapper().map(userToken, UserTokenDto.class);
        // 存入redis数据库
        updateRedisUserToken(accessToken, accessTokenValidateSeconds, userTokenDto);
    }

    private void updateRedisUserToken(String accessToken, int accessTokenValidateSeconds, UserTokenDto userTokenDto) {
        redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(accessToken), userTokenDto, accessTokenValidateSeconds, TimeUnit.SECONDS);
    }

    @Override
    public UserTokenDto getByAccessToken(String accessToken) {
        UserTokenDto userTokenDto = (UserTokenDto) redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(accessToken));
        if (userTokenDto == null) {
            UserToken userToken = new UserToken();
            userToken.setAccessToken(accessToken);
            userToken = userUserTokenMapper.selectOne(userToken);
            userTokenDto = new ModelMapper().map(userToken, UserTokenDto.class);
        }
        return userTokenDto;
    }

    @Override
    public void updateUserToken(UserTokenDto tokenDto, LoginAuthDto loginAuthDto) {
        UserToken userToken = new ModelMapper().map(tokenDto, UserToken.class);
        userToken.setUpdateInfo(loginAuthDto);
        userUserTokenMapper.updateByPrimaryKeySelective(userToken);
        OAuth2ClientProperties[] clients = securityProperties.getOauth2().getClients();
        int accessTokenValidateSeconds = clients[0].getAccessTokenValidateSeconds();
        updateRedisUserToken(userToken.getAccessToken(), accessTokenValidateSeconds, tokenDto);
    }

    @Override
    public PageInfo listTokenWithPage(TokenMainQueryDto token) {
        PageHelper.startPage(token.getPageNum(), token.getPageSize());
        UserToken userToken = new UserToken();
        userToken.setStatus(token.getStatus());
        if (token.getStatus() != null) {
            userToken.setStatus(token.getStatus());
        }

        if (StringUtils.isNotBlank(token.getLoginName())) {
            userToken.setLoginName(token.getLoginName());
        }
        if (StringUtils.isNotBlank(token.getUserName())) {
            userToken.setUserName(token.getUserName());
        }
        List<UserToken> userTokenList = userUserTokenMapper.selectTokenList(userToken);
        return new PageInfo<>(userTokenList);
    }

    @Override
    public String refreshToken(String accessToken, String refreshToken, HttpServletRequest request) throws HttpProcessException {
        String token;
        Map<String, Object> map = new HashMap<>(2);
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", refreshToken);

        Header[] headers = HttpHeader.custom().contentType(HttpHeader.Headers.APP_FORM_URLENCODED).authorization(request.getHeader(HttpHeaders.AUTHORIZATION)).build();
        HttpConfig config = HttpConfig.custom().headers(headers).url(refreshTokenUrl).map(map);
        token = HttpClientUtil.post(config);
        JSONObject jsonObj = JSON.parseObject(token);
        String accessTokenNew = (String) jsonObj.get("access_token");
        String refreshTokenNew = (String) jsonObj.get("refresh_token");
        String loginName = (String) jsonObj.get("loginName");
        // 更新本次token数据
        UserTokenDto tokenDto = this.getByAccessToken(accessToken);
        tokenDto.setStatus(UserTokenStatusEnum.ON_REFRESH.getStatus());
        UserInfo userInfo = userService.findUserInfoByLoginName(loginName);

        LoginAuthDto loginAuthDto = new LoginAuthDto(userInfo.getId(), userInfo.getLoginName(),
                userInfo.getUserName(), userInfo.getGroupCode(), userInfo.getGroupName());
        this.updateUserToken(tokenDto, loginAuthDto);
        // 创建刷新token
        this.saveUserToken(accessTokenNew, refreshTokenNew, loginAuthDto, request);
        return token;
    }

    @Override
    public int batchUpdateTokenOffLine() {
        List<Long> idList = userUserTokenMapper.listOffLineTokenId();
        if (PublicUtil.isEmpty(idList)) {
            return 1;
        }
        Map<String, Object> map = Maps.newHashMap();
        map.put("status", 20);
        map.put("tokenIdList", idList);
        return userUserTokenMapper.batchUpdateTokenOffLine(map);
    }
}
