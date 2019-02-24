package org.tc.provider.mapper;

import org.tc.core.mybatis.MyMapper;
import org.tc.provider.model.domain.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserRoleMapper extends MyMapper<RoleInfo> {


    // 自定义方法，通过SQL实现
    RoleInfo findByRoleName(String roleName);

    RoleInfo findByRoleCode(String roleCode);

    void saveUserRole(final RoleInfo userRole);

    List<RoleInfo> selectUserRoleByUserId(Long userId);

    int updateUserRole(RoleInfo userRole);

    List<String> selectAllUserRoleName();

    List<String> selectAllRoleCode();


}
