package org.tc.provider.model.dto.action;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ActionDto implements Serializable {
    private static final long serialVersionUID = -8564391379580646823L;


    @ApiModelProperty(value = "权限名")
    private String actionName;

    @ApiModelProperty(value = "权限描述")
    private String actionDesc;

    @ApiModelProperty(value = "权限类型")
    private Integer actionType;

    @ApiModelProperty(value = "权限字段")
    private String actionCode;


}
