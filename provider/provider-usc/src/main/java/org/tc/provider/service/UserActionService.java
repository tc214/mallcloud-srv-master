package org.tc.provider.service;

import org.tc.base.dto.LoginAuthDto;
import org.tc.provider.model.domain.UserAction;
import org.tc.provider.model.domain.UserMenu;
import org.tc.provider.model.vo.MenuVo;
import org.tc.core.support.IService;

import java.util.List;

public interface UserActionService extends IService<UserAction> {

    UserAction getByActionCode(String action);

    int deleteActionById(Long actionId);

    void batchDeleteByIdList(List<Long> deleteIdList);

    void saveAction(UserAction action, LoginAuthDto loginAuthDto);

    int saveAction(UserAction action);

    int deleteByMenuId(Long id);

    List<Long> getCheckedActionList(Long roleId);

    List<MenuVo> getOwnAuthList(Long userId);

    List<Long> getCheckedMenuList(Long roleId);

    List<UserAction> getOwnActionListByUserId(Long userId);

    List<UserAction> listActionListByRoleId(Long roleId);

    List<UserAction> listActionList(List<UserMenu> userMenus);

}
