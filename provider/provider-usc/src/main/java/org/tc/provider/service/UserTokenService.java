package org.tc.provider.service;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.github.pagehelper.PageInfo;
import org.tc.base.dto.LoginAuthDto;
import org.tc.base.dto.UserTokenDto;
import org.tc.core.support.IService;
import org.tc.provider.model.domain.UserToken;
import org.tc.provider.model.dto.token.TokenMainQueryDto;

import javax.servlet.http.HttpServletRequest;

public interface UserTokenService extends IService<UserToken> {

    void saveUserToken(String accessToken, String refreshToken, LoginAuthDto loginAuthDto, HttpServletRequest request);

    UserTokenDto getByAccessToken(String accessToken);


    void updateUserToken(UserTokenDto tokenDto, LoginAuthDto loginAuthDto);

    PageInfo listTokenWithPage(TokenMainQueryDto token);

    String refreshToken(String accessToken, String refreshToken, HttpServletRequest request)
            throws HttpProcessException;

    int batchUpdateTokenOffLine();

}
