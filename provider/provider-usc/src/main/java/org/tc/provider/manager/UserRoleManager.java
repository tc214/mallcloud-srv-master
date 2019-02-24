package org.tc.provider.manager;

import org.tc.provider.mapper.UserRoleMapper;
import org.tc.provider.model.domain.RoleInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色管理
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class UserRoleManager {
    @Resource
    private UserRoleMapper userRoleMapper;


    public void saveUserRole(final RoleInfo userRole) {
        log.info("保存角色信息.role={}", userRole);
        userRoleMapper.insertSelective(userRole);
    }

    public RoleInfo getUserRoleById(final Long id) {
        log.info("角色id", id);
        return userRoleMapper.selectByPrimaryKey(id);
    }

    public RoleInfo findByRoleName(final String roleName) {
        log.info("角色id", roleName);
        return userRoleMapper.findByRoleName(roleName);
    }

    public RoleInfo findByRoleCode(final String roleCode) {
        log.info("角色id", roleCode);
        return userRoleMapper.findByRoleCode(roleCode);
    }

    public int updateUserRole(final RoleInfo userRole) {
        log.info("更新角色.role={}", userRole);

        return userRoleMapper.updateByPrimaryKeySelective(userRole);
    }

    public int deleteUserRole(final String roleId) {
        return userRoleMapper.deleteByPrimaryKey(roleId);
    }

    public List<String> getAllUserRoleName() {
        return userRoleMapper.selectAllUserRoleName();
    }

    public List<String> selectAllRoleCode() {
        return userRoleMapper.selectAllRoleCode();
    }
}
