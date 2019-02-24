package org.tc.provider.model.dto.bind;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class UserRoleBindDto implements Serializable {
    private static final long serialVersionUID = -1763138249842735256L;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;


}
