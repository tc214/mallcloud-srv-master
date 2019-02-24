package org.tc.provider.mapper;

import org.tc.core.mybatis.MyMapper;
import org.tc.provider.model.domain.UserAction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface User action mapper.
 */
@Component
@Mapper
public interface UserActionMapper extends MyMapper<UserAction> {

    //
    UserAction selectByAction(String actionCode);

//	List<Perm> findAllPerms();

//	List<String> findActionCodeListByUserId(Long userId);

    int batchDeleteByIdList(@Param("idList") List<Long> deleteIdList);

//	List<ActionVo> queryActionListWithPage(UserAction userAction);

//	int deleteByMenuId(@Param("menuId") Long id);

//	List<Long> getCheckedActionList(@Param("roleId") Long roleId);
//
//	List<MenuVo> getOwnAuthList(@Param("userId") Long userId);
//
//	List<Long> getCheckedMenuList(@Param("roleId") Long roleId);
//
//	List<UserAction> getOwnUserActionListByUserId(Long userId);
//
//	List<UserAction> listActionListByRoleId(@Param("roleId") Long roleId);

//	List<UserAction> listActionList(@Param("menuList") List<UserMenu> menuList);
}