package org.tc.provider.controller;


import org.tc.provider.model.domain.RoleInfo;
import org.tc.provider.model.dto.role.RoleDto;
import org.tc.provider.service.UserRoleService;
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
@RequestMapping(value = "/usc/role")
public class RoleController {

    @Resource
    private UserRoleService userRoleService;


    @PostMapping("/add")
    public Wrapper addRoleName(@RequestBody RoleDto dto) {
        String roleName = dto.getRoleName();
        String roleDesc = dto.getRoleDesc();
        String roleCode = dto.getRoleCode();
        if (StringUtil.isBlank(roleName)) {
            return WrapMapper.error("role name cannot be null!");
        }
        if (StringUtil.isBlank(roleCode)) {
            return WrapMapper.error("role code cannot be null!");
        }
        Date date = new Date();
        RoleInfo userRole = new RoleInfo();
//        userRole.setRoleId("133213L);
        userRole.setRoleName(roleName);
        userRole.setRoleCode(roleCode);
        userRole.setRoleDesc(roleDesc);
        userRole.setCreatedTime(date);
        userRole.setUpdatedTime(date);
        userRoleService.addUserRole(userRole);
        return WrapMapper.ok();
    }

    @PostMapping("/del")
    public Wrapper delRoleName(@RequestBody RoleDto dto) {
        String roleName = dto.getRoleName();
        RoleInfo userRole = userRoleService.findByRoleName(roleName);
        if (userRole == null) {
            return WrapMapper.error("role doesn't exist!");
        }
        String userRoleCode = userRole.getRoleCode();
        userRoleService.deleteUserRoleById(userRoleCode);
        // delete the role from RoleAction table and RoleInfo
        return WrapMapper.ok();
    }

    @PostMapping("/edit")
    public Wrapper editRoleName(@RequestBody RoleDto dto) {
        String roleName = dto.getRoleName();
        String roleCode = dto.getRoleCode();
        String roleDesc = dto.getRoleDesc();
        RoleInfo userRole = userRoleService.findByRoleName(roleName);

        Date nowtime = new Date();
        userRole.setRoleCode(roleCode);
        userRole.setRoleName(roleName);
        userRole.setRoleDesc(roleDesc);
        userRole.setUpdatedTime(nowtime);
        userRoleService.updateUserRole(userRole);
        return WrapMapper.ok();
    }

    @PostMapping("/name")
    public Wrapper<RoleInfo> getRoleInfoByName(@RequestBody RoleDto dto) {
        String roleName = dto.getRoleName();
        RoleInfo roleInfo = userRoleService.findByRoleName(roleName);
        return WrapMapper.ok(roleInfo);
    }

    @PostMapping("/code")
    public Wrapper<RoleInfo> getRoleInfoByCode(@RequestBody RoleDto dto) {
        String roleCode = dto.getRoleCode();
        RoleInfo roleInfo = userRoleService.findByRoleCode(roleCode);
        return WrapMapper.ok(roleInfo);
    }

    @RequestMapping("/names")
    public Wrapper<List<String>> getAllRoleName() {
        List<String> roleNameList;
        roleNameList = userRoleService.getAllRoleName();
        return WrapMapper.ok(roleNameList);
    }

    @RequestMapping("/codes")
    public Wrapper<List<String>> getAllRoleCode() {
        List<String> roleCodeList;
        roleCodeList = userRoleService.getAllRoleCode();
        return WrapMapper.ok(roleCodeList);
    }

    @RequestMapping("/infos")
    public Wrapper<List<RoleInfo>> getAllRoleInfo() {
        List<RoleInfo> roleInfoList;
        roleInfoList = userRoleService.selectAll();
        return WrapMapper.ok(roleInfoList);
    }


}
