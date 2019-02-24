package org.tc.provider.model.dto.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@ApiModel(value = "单位注册dto")
public class GroupRegisterDto implements Serializable {

    private static final long serialVersionUID = -1019192329449610380L;

    @ApiModelProperty(value = "单位名")
    private String groupName;

    @ApiModelProperty(value = "单位类型")
    private String groupType;

    @ApiModelProperty(value = "单位证件号")
    private String liscenseNum;

    @ApiModelProperty(value = "单位座机")
    private String groupTele;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "单位邮箱")
    private String groupEmail;

    @ApiModelProperty(value = "单位地址")
    private String groupAddress;

    @ApiModelProperty(value = "单位邮编")
    private String zipCode;

    @ApiModelProperty(value = "项目名")
    private String projectName;

    @ApiModelProperty(value = "项目合同")
    private String projectContract;

    @ApiModelProperty(value = "项目开始时间")
    private String projectStartDate;

    @ApiModelProperty(value = "项目结束时间")
    private String projectEndDate;


}
