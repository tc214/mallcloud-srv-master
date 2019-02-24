package org.tc.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户登录Dto")
public class UserLoginDto implements Serializable {

    private static final long serialVersionUID = -1890868423523889760L;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    private String loginName;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "密码")
    private String loginPwd;


}
