package org.tc.provider.service;

import org.tc.core.support.IService;
import org.tc.provider.model.domain.RoleInfo;

import java.util.List;


/**
 * The interface role service.
 *
 * @author tancan
 */
public interface UserRoleService extends IService<RoleInfo> {

    int addUserRole(final RoleInfo userRole);

    int deleteUserRoleById(String roleId);

    int updateUserRole(RoleInfo userRole);

    RoleInfo findByRoleName(String roleName);

    RoleInfo findByRoleCode(String roleCode);

    RoleInfo queryByRoleId(Long roleId);


    List<String> getAllRoleName();

    List<String> getAllRoleCode();
}
