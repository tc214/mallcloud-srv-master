package org.tc.provider.controller;


import org.tc.provider.model.domain.UserInfo;
import org.tc.provider.model.domain.UserRoleBind;
import org.tc.provider.model.dto.bind.UserRoleBindDto;
import org.tc.provider.model.dto.user.LoginInfoDto;
import org.tc.provider.service.UserRoleBindService;
import org.tc.provider.service.UserService;
import org.tc.util.StringUtil;
import org.tc.wrapper.WrapMapper;
import org.tc.wrapper.Wrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/usc/user-role")
public class UserRoleBindController {

    @Resource
    private UserRoleBindService userRoleBindService;

    @Resource
    private UserService userService;


    @PostMapping("/add")
    public Wrapper addUserRole(@RequestBody UserRoleBindDto dto) {
        Long userId = dto.getUserId();
        String roleCode = dto.getRoleCode();
        if (StringUtil.isBlank(roleCode)) {
            return WrapMapper.error("角色编码不能为空");
        }
        if (userId == null || userId <= 0) {
            return WrapMapper.error("用户id不合法");
        }

        //检查用户存在

        //检查角色存在

        //检查该绑定是否存在
        UserRoleBind userRoleBindOld = userRoleBindService.findByUserIdRoleCode(userId, roleCode);
        if (userRoleBindOld != null) {
            return WrapMapper.error("该用户已经绑定指定角色");
        }
        UserRoleBind userRoleBind = new UserRoleBind();
        userRoleBind.setRoleCode(roleCode);
        userRoleBind.setUserId(userId);
        Date nowtime = new Date();
        userRoleBind.setCreatedTime(nowtime);
        userRoleBind.setUpdatedTime(nowtime);
        userRoleBindService.addUserRoleBind(userRoleBind);

        return WrapMapper.ok();
    }


    @PostMapping("/get")
    public Wrapper<List<String>> getUserRoleByLoginName(@RequestBody LoginInfoDto dto) {
        String loginName = dto.getLoginName();
        if (StringUtil.isBlank(loginName)) {
            return WrapMapper.error("登录名为空!");
        }

        UserInfo userInfo = userService.findByLoginName(loginName);
        if (userInfo == null) {
            return WrapMapper.error("用户不存在");
        }

        List<String> roleList = userRoleBindService.findByUserId(userInfo.getId());
//        if (roleList == null) {
//            WrapMapper.error();
//        }
        return WrapMapper.ok(roleList);
    }

}
