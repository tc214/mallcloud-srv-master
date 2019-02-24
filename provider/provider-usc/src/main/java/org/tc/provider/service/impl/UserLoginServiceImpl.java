package org.tc.provider.service.impl;

import com.google.common.base.Preconditions;
import org.tc.base.dto.LoginAuthDto;
import org.tc.base.enums.ErrorCodeEnum;
import org.tc.provider.service.UserLoginService;
import org.tc.util.PublicUtil;
import org.tc.provider.model.domain.UserInfo;
import org.tc.provider.model.dto.user.LoginRespDto;
import org.tc.provider.model.exceptions.UserBizException;
import org.tc.provider.model.vo.MenuVo;
import org.tc.provider.security.SecurityUtils;
import org.tc.provider.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private UserService userService;


    @Override
    public LoginRespDto loginAfter(Long applicationId) {
        LoginRespDto loginRespDto = new LoginRespDto();
        String loginName = SecurityUtils.getCurrentLoginName();
        if (StringUtils.isEmpty(loginName)) {
            log.error("操作超时, 请重新登录 loginName={}", loginName);
            Preconditions.checkArgument(StringUtils.isNotEmpty(loginName), "操作超时, 请重新登录");
        }

        UserInfo uscUser = userService.findByLoginName(loginName);
        if (PublicUtil.isEmpty(uscUser)) {
            log.info("找不到用户信息 loginName={}", loginName);
            throw new UserBizException(ErrorCodeEnum.USER10011002, loginName);
        }

        LoginAuthDto loginAuthDto = this.getLoginAuthDto(uscUser);
        List<MenuVo> menuVoList = null;//todo
        loginRespDto.setLoginAuthDto(loginAuthDto);
        loginRespDto.setMenuList(menuVoList);
        return loginRespDto;
    }

    private LoginAuthDto getLoginAuthDto(UserInfo uscUser) {
        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setUserId(uscUser.getId());
        loginAuthDto.setUserName(uscUser.getUserName());
        loginAuthDto.setLoginName(uscUser.getLoginName());
        return loginAuthDto;
    }


}
