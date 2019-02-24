package org.tc.provider.service.impl;


import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.xiaoleilu.hutool.date.DateUtil;
import org.tc.provider.manager.UserManager;
import org.tc.provider.model.dto.UserRegisterDto;
import org.tc.util.PingyinUtil;
import org.tc.util.RedisKeyUtil;
import org.tc.base.dto.LoginAuthDto;
import org.tc.base.enums.ErrorCodeEnum;
import org.tc.base.util.RequestUtils;
import org.tc.util.PublicUtil;
import org.tc.core.support.BaseService;
import org.tc.provider.enums.UserStatusEnum;
import org.tc.provider.mail.EmailSendService;
import org.tc.provider.manager.CompanyManager;
import org.tc.provider.mapper.UserInfoMapper;
import org.tc.provider.model.domain.CompanyInfo;
import org.tc.provider.model.domain.UserAction;
import org.tc.provider.model.domain.UserInfo;
import org.tc.provider.model.dto.UserActivateDto;
import org.tc.provider.model.dto.UserLoginDto;
import org.tc.provider.model.dto.group.GroupRegisterDto;
import org.tc.provider.model.dto.user.ResetLoginPwdDto;
import org.tc.provider.model.exceptions.UserBizException;
import org.tc.provider.security.SecurityUser;
import org.tc.provider.service.UserActionService;
import org.tc.provider.service.UserService;
import org.tc.provider.service.UserTokenService;
import org.tc.provider.utils.Md5Util;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl extends BaseService<UserInfo> implements UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserManager userManager;

    @Resource
    private CompanyManager companyManager;

    @Resource
    private TaskExecutor taskExecutor;
    @Resource
    private UserTokenService userTokenService;

    @Resource
    private UserActionService userActionService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private EmailSendService emailsendService;


    @Override
    public UserInfo findByLoginName(String loginName) {
        return userManager.getUserInfoByLoginName(loginName);
    }


    @Override
    public int updateUserInfo(UserInfo user) {
        logger.info("更新用户信息 userInfo={}", user);
        int updateResult = userInfoMapper.updateByPrimaryKeySelective(user);
//        updateResult = userManager.updateUserInfo(user);
        if (updateResult < 1) {
            logger.info("用户【 {} 】修改用户信息失败", user.getId());
        } else {
            logger.info("用户【 {} 】修改用户信息成功", user.getId());
        }
        return updateResult;
    }

    // todo
    @Override
    public int deleteUserById(Long userId) {
        return userManager.deleteUserInfoById(userId);
    }

    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        return userManager.getUserInfoById(userId);
    }

    @Override
    public UserInfo queryByUserId(Long userId) {
        return userManager.getUserInfoById(userId);
    }

    @Override
    public void userRegister(UserRegisterDto registerDto) {
        // 检查注册信息
        validateUserRegisterInfo(registerDto);

        String mobileNo = registerDto.getMobileNo();
        String email = registerDto.getEmail();
        Date date = new Date();
        String loginPwd = registerDto.getLoginPwd();
        loginPwd = Md5Util.encrypt(loginPwd);

        // 封装注册信息
        long userId = generateId();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setLoginName(registerDto.getLoginName());
        userInfo.setLoginPwd(loginPwd);
        userInfo.setUserName(registerDto.getUserName());
        userInfo.setEmail(email);
        userInfo.setMobileNo(mobileNo);
        userInfo.setPermissions("");
        userInfo.setIdCard(registerDto.getIdCard());
        userInfo.setGroupCode(100201L);
        userInfo.setGroupType(registerDto.getGroupType());
        userInfo.setGroupName(registerDto.getGroupName());
        userInfo.setGroupTele(registerDto.getGroupTele());
        userInfo.setStatus(UserStatusEnum.DISABLE.getKey());
        userInfo.setRegistTime(date);
        userInfo.setCreatedTime(date);
        userInfo.setUpdatedTime(date);
//        uscUser.setActiveCode("");

        // 保存用户信息
        userManager.saveUserInfo(userInfo);
        // 发送激活邮件
        emailsendService.sendActiveEmail(userInfo);
    }

    @Override
    public void groupRegister(GroupRegisterDto dto) {
        validateGroupRegisterInfo(dto);
        // 封装组信息
        long groupId = generateId();
        Date date = new Date();
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setId(groupId);
        companyInfo.setGroupName(dto.getGroupName());
        companyInfo.setGroupAddress(dto.getGroupAddress());
        companyInfo.setGroupEmail(dto.getGroupEmail());
        companyInfo.setGroupTele(dto.getGroupTele());
        companyInfo.setGroupType(dto.getGroupType());
        companyInfo.setFax(dto.getFax());
        companyInfo.setLiscenseNum(dto.getLiscenseNum());
        companyInfo.setProjectName(dto.getProjectName());
        companyInfo.setProjectContract(dto.getProjectContract());
        companyInfo.setProjectStartDate(dto.getProjectStartDate());
        companyInfo.setProjectEndDate(dto.getProjectEndDate());
        companyInfo.setStatus(UserStatusEnum.DISABLE.getKey());
        companyInfo.setZipCode(dto.getZipCode());
        companyInfo.setCreatedTime(date);
        companyInfo.setUpdatedTime(date);
        String headLetters = PingyinUtil.getPinYinHeadChar(companyInfo.getGroupName());
        String companyCode = dto.getGroupType() + headLetters + companyInfo.getGroupTele();
        companyInfo.setGroupCode(companyCode);

        // 保存单位信息
        companyManager.saveCompanyInfo(companyInfo);
        // 发送单位注册审核通知邮件
        emailsendService.sendCompRegCheckEmail(companyInfo);
    }

    @Override
    public boolean checkLoginName(String loginName) {
        UserInfo uscUser = findByLoginName(loginName);
        return uscUser != null;
    }

    @Override
    public boolean checkEmail(String loginName) {
        return false;
    }

    @Override
    public boolean checkMobileNo(String validValue) {
        return false;
    }


    @Override
    public String activateUser(String activeToken) {
        String res = "激活失败";
        if (PublicUtil.isStrEmptyOrNull(activeToken)) {
            return "token为空";
        }

        // 校验激活码
        String key = RedisKeyUtil.getActiveUserKey(activeToken);
        Object object = redisTemplate.opsForValue().get(key);
        UserInfo userInfo = (UserInfo) object;
        if (userInfo == null) {
            return "用户不存在";
        }

        Long expire = redisTemplate.getExpire(key, TimeUnit.HOURS);
        if (expire <= 0) {
            return "激活链接已超时";
        }
        if (UserStatusEnum.ENABLE.getKey().equalsIgnoreCase(userInfo.getStatus())) {
            return "账户已激活";
        }
        if (activeToken.equalsIgnoreCase(userInfo.getActiveCode())) {
            userInfo.setActiveCode("");
            userInfo.setStatus(UserStatusEnum.ENABLE.getKey());
            userInfo.setActivatedTime(new Date());
            userManager.updateUserInfo(userInfo);
            return "";
        }
        return res;
    }

    @Override
    public String activateUser(UserActivateDto userActivateDto) {
        Long userId = userActivateDto.getUserId();
        String activateCode = userActivateDto.getActiveCode();
        if (userId == null || PublicUtil.isStrEmptyOrNull(activateCode)) {
            return null;
        }

        // 校验用户ID和激活码
        UserInfo user = userManager.getUserInfoById(userId);
        if (user == null) {
            return "用户不存在";
        }
        if (UserStatusEnum.ENABLE.getKey().equalsIgnoreCase(user.getStatus())) {
            return "账户已激活";
        }
        if (activateCode.equalsIgnoreCase(user.getActiveCode())) {
            user.setActiveCode("");
            user.setStatus(UserStatusEnum.ENABLE.getKey());
            user.setActivatedTime(new Date());
            userManager.updateUserInfo(user);
            return "激活成功";
        }
        return "激活失败";
    }

    @Override
    public UserInfo findUserInfoByLoginName(String loginName) {
        return null;
    }


    // todo
    private void validateUserRegisterInfo(UserRegisterDto registerDto) {

    }

    private void validateGroupRegisterInfo(GroupRegisterDto registerDto) {

    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        String loginName = userLoginDto.getLoginName();
        String loginPwd = userLoginDto.getLoginPwd();

        // checkout loginName and loginPwd
        UserInfo uscUser = findByLoginName(loginName);
        if (uscUser != null) {
            if (!Md5Util.checkoutPwd(loginPwd, uscUser.getLoginPwd())) {
                return "登录失败：密码不匹配";
            }
        } else {
            return "登录失败：用户不存在";
        }
        return "";
    }

    @Override
    public Collection<GrantedAuthority> loadUserAuthorities(Long userId) {
        List<UserAction> ownAuthList = userActionService.getOwnActionListByUserId(userId);
        List<GrantedAuthority> authList = Lists.newArrayList();
        for (UserAction action : ownAuthList) {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(action.getUrl());
//            authList.add(grantedAuthority);
        }
        if (authList.isEmpty()) { //todo

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("/user/save");
            authList.add(grantedAuthority);
        }
        return authList;
    }

    @Override
    public void resetLoginPwd(ResetLoginPwdDto resetLoginPwdDto) {
        String resetPwdKey = resetLoginPwdDto.getResetPwdKey();
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(RedisKeyUtil.getResetPwdTokenKey(resetPwdKey));
        if (userInfo == null) {
            logger.error("找不到用户. userInfo={}", userInfo);
            throw new UserBizException(ErrorCodeEnum.USER10011003, userInfo);
        }
        String newLoginPwd = resetLoginPwdDto.getNewPassword();
        String confirmPwd = resetLoginPwdDto.getConfirmPwd();

        UserInfo update = new UserInfo();
        update.setId(userInfo.getId());
        update.setLoginPwd(Md5Util.encrypt(newLoginPwd));
        short isChangedPwd = 0;
//        update.setIsChangedPwd(isChangedPwd);

        Map<String, Object> param = Maps.newHashMap();
        param.put("loginName", userInfo.getLoginName());
        param.put("newLoginPwd", newLoginPwd);
        param.put("dateTime", DateUtil.formatDateTime(new Date()));

        Set<String> to = Sets.newHashSet();
        to.add(userInfo.getEmail());

        userManager.resetLoginPwd(update, param);
    }

    /**
     * 重置密码
     *
     * @param userId       the user id
     * @param loginAuthDto the login auth dto
     */
    @Override
    public void resetLoginPwd(Long userId, LoginAuthDto loginAuthDto) {
        if (userId == null) {
            throw new UserBizException(ErrorCodeEnum.USER10011001);
        }
        UserInfo userInfo = this.queryByUserId(userId);
        if (userInfo == null) {
            logger.error("找不到用户. userId={}", userId);
            throw new UserBizException(ErrorCodeEnum.USER10011003, userId);
        }

        Preconditions.checkArgument(!StringUtils.isEmpty(userInfo.getEmail()), "邮箱地址不能为空");
        // todo
    }

    @Override
    public void handlerLoginData(OAuth2AccessToken token, final SecurityUser principal,
                                 HttpServletRequest request) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        final String os = userAgent.getOperatingSystem().getName();
        final String browser = userAgent.getBrowser().getName();
        final String remoteAddr = RequestUtils.getRemoteAddr(request);
        final String remoteLocation = "remoteLocation";
        final String requestURI = request.getRequestURI();//  /auth/form

        UserInfo userInfo = new UserInfo();
        Long userId = principal.getUserId();
//        userInfo.setLastLoginIp(remoteAddr);
        userInfo.setId(userId);
//        userInfo.setLastLoginTime(new Date());
//        userInfo.setLastLoginLocation(remoteLocation);
        LoginAuthDto loginAuthDto = new LoginAuthDto(userId, principal.getLoginName(), principal.getNickName(), principal.getGroupId(), principal.getGroupName());
        // 记录token日志
        String accessToken = token.getValue();
        String refreshToken = token.getRefreshToken().getValue();
        userTokenService.saveUserToken(accessToken, refreshToken, loginAuthDto, request);
        // 记录最后登录信息
        taskExecutor.execute(() -> this.updateUserInfo(userInfo));
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
        // todo
        return null;
    }
}
