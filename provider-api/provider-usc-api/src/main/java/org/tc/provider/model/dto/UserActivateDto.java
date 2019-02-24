package org.tc.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户激活Dto")
public class UserActivateDto implements Serializable {

    private static final long serialVersionUID = -7402813996873721151L;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 激活码
     */
    @ApiModelProperty(value = "激活码")
    private String activeCode;

    /**
     * token
     */
    @ApiModelProperty(value = "授权码")
    private String token;


}
