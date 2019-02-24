package org.tc.provider.service.impl;


import org.tc.core.support.BaseService;
import org.tc.provider.manager.UserRoleManager;
import org.tc.provider.model.domain.RoleInfo;
import org.tc.provider.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UserRoleServiceImpl extends BaseService<RoleInfo> implements UserRoleService {

    @Resource
    UserRoleManager userRoleManager;


    @Override
    public int addUserRole(final RoleInfo userRole) {
        userRoleManager.saveUserRole(userRole);
        return 0;
    }

    @Override
    public int deleteUserRoleById(String roleId) {
        return userRoleManager.deleteUserRole(roleId);
    }

    @Override
    public int updateUserRole(RoleInfo userRole) {
        return userRoleManager.updateUserRole(userRole);
    }

    @Override
    public RoleInfo findByRoleName(String roleName) {
        return userRoleManager.findByRoleName(roleName);
    }

    @Override
    public RoleInfo queryByRoleId(Long roleId) {
        return userRoleManager.getUserRoleById(roleId);
    }

    @Override
    public List<String> getAllRoleName() {
        return userRoleManager.getAllUserRoleName();
    }

    @Override
    public List<String> getAllRoleCode() {
        return userRoleManager.selectAllRoleCode();
    }

    @Override
    public RoleInfo findByRoleCode(String roleCode) {
        return userRoleManager.findByRoleCode(roleCode);
    }
}
