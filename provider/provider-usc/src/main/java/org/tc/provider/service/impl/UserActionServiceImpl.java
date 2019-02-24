package org.tc.provider.service.impl;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import org.tc.base.constant.GlobalConstant;
import org.tc.base.dto.LoginAuthDto;
import org.tc.base.enums.ErrorCodeEnum;
import org.tc.core.support.BaseService;
import org.tc.provider.mapper.UserActionMapper;
import org.tc.provider.model.domain.UserAction;
import org.tc.provider.model.domain.UserMenu;
import org.tc.provider.model.exceptions.UserBizException;
import org.tc.provider.model.vo.MenuVo;
import org.tc.provider.service.UserActionService;
import org.tc.util.PublicUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class user action service.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserActionServiceImpl extends BaseService<UserAction> implements UserActionService {
    @Resource
    private UserActionMapper userActionMapper;
//	@Resource
//	private UserRoleActionMapper userRoleActionMapper;


    @Override
    public int deleteActionById(Long actionId) {
        if (null == actionId) {
            throw new IllegalArgumentException("权限ID不能为空");
        }

        UserAction userAction = userActionMapper.selectByPrimaryKey(actionId);
        if (userAction == null) {
            logger.error("找不到权限信息 actionId={}", actionId);
            throw new UserBizException(ErrorCodeEnum.USER10014001, actionId);
        }

//		userRoleActionMapper.deleteByActionId(actionId);
        return userActionMapper.deleteByPrimaryKey(actionId);
    }

    @Override
    public void batchDeleteByIdList(List<Long> deleteIdList) {
        Preconditions.checkArgument(PublicUtil.isNotEmpty(deleteIdList), "删除权限ID不能为空");
//		int result = userActionMapper.batchDeleteByIdList(deleteIdList);
        int result = 0;
        if (result < deleteIdList.size()) {
            throw new UserBizException(ErrorCodeEnum.USER10014002, Joiner.on(GlobalConstant.Symbol.COMMA).join(deleteIdList));
        }
    }

    @Override
    public void saveAction(UserAction action, LoginAuthDto loginAuthDto) {
//		List<Long> menuIdList = action.getMenuIdList();
        List<Long> menuIdList = null;
        Long menuId;
        Preconditions.checkArgument(PublicUtil.isNotEmpty(menuIdList), "菜单名称不能为空");
        menuId = menuIdList.get(menuIdList.size() - 1);
//		action.setMenuId(menuId);
        action.setUpdateInfo(loginAuthDto);
        if (action.isNew()) {
            Long actionId = super.generateId();
            action.setId(actionId);
            userActionMapper.insertSelective(action);
        } else {
            int result = userActionMapper.updateByPrimaryKeySelective(action);
            if (result < 1) {
                throw new UserBizException(ErrorCodeEnum.USER10014003);
            }
        }
    }

    @Override
    public int saveAction(UserAction action) {
        return userActionMapper.insert(action);
    }

    @Override
    public int deleteByMenuId(Long id) {
        Preconditions.checkArgument(id != null, "菜单ID不能为空");
//		return userActionMapper.deleteByMenuId(id);
        return 0;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Long> getCheckedActionList(Long roleId) {
        if (roleId == null) {
            throw new UserBizException(ErrorCodeEnum.USER10012001);
        }
//		return userActionMapper.getCheckedActionList(roleId);
        return null;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<MenuVo> getOwnAuthList(Long userId) {
//		return userActionMapper.getOwnAuthList(userId);
        return null;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Long> getCheckedMenuList(Long roleId) {
        if (roleId == null) {
            throw new UserBizException(ErrorCodeEnum.USER10012001);
        }
//		return userActionMapper.getCheckedMenuList(roleId);
        return null;
    }

    @Override
    public List<UserAction> getOwnActionListByUserId(Long userId) {
        if (userId == null) {
            throw new UserBizException(ErrorCodeEnum.USER10011001);
        }
        List<UserAction> userActionList;
        if (Objects.equals(userId, GlobalConstant.Sys.SUPER_MANAGER_USER_ID)) {
            userActionList = userActionMapper.selectAll();
        } else {
//			userActionList = userActionMapper.getOwnUserActionListByUserId(userId);
//			userActionList = null; //todo
            userActionList = new ArrayList<UserAction>();
        }
        return userActionList;
    }

    @Override
    public List<UserAction> listActionListByRoleId(Long roleId) {
//		return userActionMapper.listActionListByRoleId(roleId);
        return null;
    }

    @Override
    public List<UserAction> listActionList(List<UserMenu> userMenus) {
//		return userActionMapper.listActionList(userMenus);
        return null;
    }

    @Override
    public UserAction getByActionCode(String action) {
        return userActionMapper.selectByAction(action);
    }
}