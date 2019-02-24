package org.tc.provider.model.dto.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel
public class UserEmailVerifyDto implements Serializable {

    private static final long serialVersionUID = 5487096167602874283L;


    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

}
