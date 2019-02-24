package org.tc.provider.controller;

import org.tc.provider.model.domain.UserAction;
import org.tc.provider.model.dto.action.ActionDto;
import org.tc.provider.service.UserActionService;
import org.tc.util.StringUtil;
import org.tc.wrapper.WrapMapper;
import org.tc.wrapper.Wrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping(value = "/usc/action")
public class ActionController {

    @Resource
    UserActionService userActionService;


    @PostMapping("/add")
    public Wrapper addAction(@RequestBody ActionDto dto) {
        String actionName = dto.getActionName();
        String actionDesc = dto.getActionDesc();
        Integer actionType = dto.getActionType();
        String action = dto.getActionCode();

        Date nowtime = new Date();
        UserAction userAction = new UserAction();
        userAction.setActionDesc(actionDesc);
        userAction.setActionCode(action);
        userAction.setPid(0L);//todo
        userAction.setActionName(actionName);
        userAction.setActionType(actionType);
        userAction.setCreatedTime(nowtime);
        userAction.setUpdatedTime(nowtime);
        userActionService.saveAction(userAction);
        return WrapMapper.ok();
    }

    @PostMapping("/del")
    public Wrapper delAction(@RequestBody ActionDto dto) {
        String actionCode = dto.getActionCode();
        if (StringUtil.isBlank(actionCode)) {
            return WrapMapper.error("Action cannot be null!");
        }
        UserAction userAction = userActionService.getByActionCode(actionCode);
        if (userAction == null) {
            return WrapMapper.error("The action doesn't exist!");
        }
        userActionService.deleteActionById(userAction.getId());
        return WrapMapper.ok();
    }

    @PostMapping("/edit")
    public Wrapper editAction() {


        return WrapMapper.ok();
    }

    @RequestMapping("/get")
    public Wrapper getAction() {


        return WrapMapper.ok();
    }


}
