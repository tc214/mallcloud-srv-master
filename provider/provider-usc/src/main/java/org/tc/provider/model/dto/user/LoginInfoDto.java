package org.tc.provider.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class LoginInfoDto implements Serializable {

    @ApiModelProperty(value = "登录名")
    private String loginName;


}
