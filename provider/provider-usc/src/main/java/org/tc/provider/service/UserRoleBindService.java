package org.tc.provider.service;

import org.tc.core.support.IService;
import org.tc.provider.model.domain.UserRoleBind;

import java.util.List;


/**
 * The interface user role bind service.
 *
 * @author tancan
 */
public interface UserRoleBindService extends IService<UserRoleBind> {

    int addUserRoleBind(UserRoleBind userRoleBind);

    List<String> findByUserId(Long userId);

    //    int deleteUserRoleById(String roleId);
//
//    int updateUserRole(RoleInfo userRole);
//
    UserRoleBind findByUserIdRoleCode(Long userId, String roleCode);
//    RoleInfo findByRoleCode(String roleCode);


}
