package org.tc.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户注册Dto")
public class UserRegisterDto implements Serializable {
    private static final long serialVersionUID = 5019025037057525808L;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String loginPwd;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobileNo;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    private String confirmPwd;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 身份证
     */
    @ApiModelProperty(value = "身份证")
    private String idCard;

    /**
     * 组名称
     */
    @ApiModelProperty(value = "组名称")
    private String groupName;

    /**
     * 组类型
     */
    @ApiModelProperty(value = "组类型")
    private String groupType;

    /**
     * 组电话
     */
    @ApiModelProperty(value = "组电话")
    private String groupTele;

    /**
     * 组编号
     */
    @ApiModelProperty(value = "组编号")
    private String groupId;


}
