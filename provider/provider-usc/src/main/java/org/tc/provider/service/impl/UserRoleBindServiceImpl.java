package org.tc.provider.service.impl;

import org.tc.core.support.BaseService;
import org.tc.provider.mapper.UserRoleBindMapper;
import org.tc.provider.model.domain.UserRoleBind;
import org.tc.provider.service.UserRoleBindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserRoleBindServiceImpl extends BaseService<UserRoleBind> implements UserRoleBindService {


    @Resource
    private UserRoleBindMapper userRoleBindMapper;


    @Override
    public int addUserRoleBind(UserRoleBind userRoleBind) {
        return userRoleBindMapper.insert(userRoleBind);
    }

    @Override
    public UserRoleBind findByUserIdRoleCode(Long userId, String roleCode) {
        return userRoleBindMapper.selectByUserIdRoleCode(userId, roleCode);
    }

    @Override
    public List<String> findByUserId(Long userId) {
        return userRoleBindMapper.selectByUserId(userId);
    }
}
