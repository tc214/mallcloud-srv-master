package org.tc.provider.controller;

import com.google.common.base.Preconditions;
import org.tc.base.dto.CheckValidDto;
import org.tc.provider.enums.UserStatusEnum;
import org.tc.provider.mail.EmailSendService;
import org.tc.provider.model.constant.UscApiConstant;
import org.tc.provider.model.domain.UserInfo;
import org.tc.provider.model.dto.UserRegisterDto;
import org.tc.provider.model.dto.user.ResetLoginPwdDto;
import org.tc.provider.service.UserService;
import org.tc.wrapper.WrapMapper;
import org.tc.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 不需认证的URL请求.
 */
@RestController
@RequestMapping(value = "/usc/auth")
@Api(value = "Web-AuthRestController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthRestController extends BaseController {
    @Resource
    private UserService uscUserService;

    @Resource
    private EmailSendService sendMailService;

    /**
     * 校验邮箱是否激活.
     *
     * @param email the email
     * @return the wrapper
     */
    @PostMapping(value = "/checkEmailActive")
    @ApiOperation(httpMethod = "POST", value = "校验邮箱")
    public Wrapper<Boolean> checkEmailActive(@RequestParam("email") String email) {
        System.out.println("checkEmailActive-start");
        UserInfo userInfo = new UserInfo();
        userInfo.setStatus(UserStatusEnum.ENABLE.getKey());
        userInfo.setEmail(email);
        int count = uscUserService.selectCount(userInfo);
        return WrapMapper.ok(count > 0);
    }

    /**
     * 校验数据.
     *
     * @param checkValidDto the check valid dto
     * @return the wrapper
     */
    @PostMapping(value = "/checkValid")
    @ApiOperation(httpMethod = "POST", value = "校验数据")
    public Wrapper checkValid(@RequestBody CheckValidDto checkValidDto) {
        String type = checkValidDto.getType();
        String validValue = checkValidDto.getValidValue();

        Preconditions.checkArgument(StringUtils.isNotEmpty(validValue), "参数错误");
        String message = null;
        boolean result = false;
        //开始校验
        if (UscApiConstant.Valid.LOGIN_NAME.equals(type)) {
            result = uscUserService.checkLoginName(validValue);
            if (!result) {
                message = "用户名已存在";
            }
        }
        if (UscApiConstant.Valid.EMAIL.equals(type)) {
            result = uscUserService.checkEmail(validValue);
            if (!result) {
                message = "邮箱已存在";
            }
        }

        if (UscApiConstant.Valid.MOBILE_NO.equals(type)) {
            result = uscUserService.checkMobileNo(validValue);
            if (!result) {
                message = "手机号码已存在";
            }
        }

        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, message, result);
    }


    /**
     * 重置密码-邮箱-提交.
     *
     * @param email the email
     * @return the wrapper
     */
    @PostMapping(value = "/submitResetPwdEmail")
    @ApiOperation(httpMethod = "POST", value = "重置密码-邮箱-提交")
    public Wrapper<String> submitResetPwdEmail(@RequestParam(value = "email") String email) {
//		logger.info("重置密码-邮箱-提交, email={}", email);
        sendMailService.submitResetPwdEmail(email);
        return WrapMapper.ok();
    }


    /**
     * 重置密码-最终提交
     *
     * @param resetLoginPwdDto the reset login pwd dto
     * @return the wrapper
     */
    @PostMapping(value = "/resetLoginPwd")
    @ApiOperation(httpMethod = "POST", value = "重置密码-最终提交")
    public Wrapper<Boolean> resetLoginPwd(ResetLoginPwdDto resetLoginPwdDto) {
        uscUserService.resetLoginPwd(resetLoginPwdDto);
        return WrapMapper.ok();
    }

    /**
     * 注册新用户
     *
     * @param user the user
     * @return the wrapper
     */
    @PostMapping(value = "/register")
    @ApiOperation(httpMethod = "POST", value = "注册新用户")
    public Wrapper register(@ApiParam(name = "registerDto", value = "用户注册Dto") @RequestBody UserRegisterDto registerDto) {
        uscUserService.userRegister(registerDto);
        return WrapMapper.ok("注册成功");
    }

    /**
     * 激活用户
     *
     * @param activeToken the active user token
     * @return the wrapper
     */
    @PostMapping(value = "/user/active")
    @ApiOperation(httpMethod = "POST", value = "激活用户")
    public Wrapper activeUser(@RequestParam("activeToken") String activeToken) {
        String res = uscUserService.activateUser(activeToken);

        if (!"".equals(res)) {
            return WrapMapper.error(res);
        }
        return WrapMapper.ok("激活成功");
    }

}
