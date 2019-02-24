package org.tc.provider.controller;

import org.tc.provider.model.dto.UserRegisterDto;
import org.tc.wrapper.WrapMapper;
import org.tc.wrapper.Wrapper;
import org.tc.provider.model.dto.group.GroupRegisterDto;
import org.tc.provider.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/usc/auth")
public class RegisterController {
    @Resource
    private UserService userService;

    /**
     * 注册新用户,不发邮件.
     *
     * @param registerDto the register dto
     * @return the wrapper
     */
    @PostMapping("/registerUser")
    @ApiOperation(httpMethod = "POST", value = "注册新用户")
    public Wrapper registerUser(@ApiParam(name = "registerDto", value = "用户注册Dto")
                                @RequestBody UserRegisterDto registerDto) {
        System.out.println("registerUser-start");
        userService.userRegister(registerDto);
        System.out.println("registerUser-end");
        return WrapMapper.ok("注册成功");
    }

    /**
     * 组注册
     *
     * @param registerDto the group register dto
     * @return Wrapper
     */
    @PostMapping("/group")
    @ApiOperation(httpMethod = "POST", value = "组注册")
    public Wrapper registerGroup(@RequestBody GroupRegisterDto registerDto) {
        System.out.println("registerGroup-start");
        userService.groupRegister(registerDto);
        return WrapMapper.ok("注册成功");
    }


}
