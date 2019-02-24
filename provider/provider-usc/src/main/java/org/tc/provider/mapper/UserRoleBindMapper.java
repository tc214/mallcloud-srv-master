package org.tc.provider.mapper;

import org.tc.core.mybatis.MyMapper;
import org.tc.provider.model.domain.UserRoleBind;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface UserRoleBindMapper extends MyMapper<UserRoleBind> {

    UserRoleBind selectByUserIdRoleCode(Long userId, String roleCode);

    List<String> selectByUserId(Long userId);

}
