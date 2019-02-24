package org.tc.provider.service;

import org.tc.base.dto.LoginAuthDto;
import org.tc.provider.model.domain.UserInfo;
import org.tc.provider.model.dto.UserActivateDto;
import org.tc.provider.model.dto.UserLoginDto;
import org.tc.provider.model.dto.UserRegisterDto;
import org.tc.provider.security.SecurityUser;
import org.tc.core.support.IService;
import org.tc.provider.model.dto.group.GroupRegisterDto;
import org.tc.provider.model.dto.user.ResetLoginPwdDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;


/**
 * The interface user service.
 *
 * @author tancan
 */
public interface UserService extends IService<UserInfo> {

    /**
     * 根据登录名查询用户信息
     *
     * @param loginName the login name
     * @return the usc user
     */
    UserInfo findByLoginName(String loginName);


    /**
     * 更新用户信息
     *
     * @param UscUser the usc user
     * @return the int
     */
    int updateUserInfo(UserInfo UscUser);


    int deleteUserById(Long userId);

    /**
     * 根据用户ID查询用户信息.
     *
     * @param userId the user id
     * @return the usc user
     */
    UserInfo findUserInfoByUserId(Long userId);


    /**
     * 根据用户ID查询用户信息.
     *
     * @param userId the user id
     * @return the usc user
     */
    UserInfo queryByUserId(Long userId);

    /**
     * 注册用户.
     *
     * @param registerDto the register dto
     */
    void userRegister(UserRegisterDto registerDto);

    void groupRegister(GroupRegisterDto dto);

    /**
     * 校验登录名是否存在.
     *
     * @param loginName the login name
     * @return the boolean
     */
    boolean checkLoginName(String loginName);

    /**
     * 校验邮箱是否存在.
     *
     * @param loginName the login name
     * @return the boolean
     */
    boolean checkEmail(String loginName);

    /**
     * 校验手机号是否存在.
     *
     * @param validValue the valid value
     * @return the boolean
     */
    boolean checkMobileNo(String validValue);

    /**
     * 激活用户.
     *
     * @param userActivateDto the active user token
     */
    String activateUser(UserActivateDto userActivateDto);

    /**
     * Find user info by login name usc user.
     *
     * @param loginName the login name
     * @return the usc user
     */
    UserInfo findUserInfoByLoginName(String loginName);

    /**
     * 用户登录
     *
     * @param userLoginDto the login dto
     * @return the login result
     */
    String login(UserLoginDto userLoginDto);

    Collection<GrantedAuthority> loadUserAuthorities(Long userId);

    /**
     * 重置登录密码.
     *
     * @param userId       the user id
     * @param loginAuthDto the login auth dto
     */
    void resetLoginPwd(Long userId, LoginAuthDto loginAuthDto);

    /**
     * 重置登录密码.
     *
     * @param resetLoginPwdDto the reset login pwd dto
     */
    void resetLoginPwd(ResetLoginPwdDto resetLoginPwdDto);

    /**
     * 激活用户.
     *
     * @param activeToken
     * @return String
     */
    String activateUser(String activeToken);

    /**
     * 处理登录数据
     */
    void handlerLoginData(OAuth2AccessToken token, final SecurityUser principal,
                          final HttpServletRequest request);

    List<UserInfo> getAllUserInfo();


}
