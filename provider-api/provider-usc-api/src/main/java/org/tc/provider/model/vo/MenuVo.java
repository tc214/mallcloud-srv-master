package org.tc.provider.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The class Menu view object.
 */
@Data
@ApiModel("菜单")
public class MenuVo implements Serializable {

    private static final long serialVersionUID = -2099147126084213856L;

    /**
     * menu.id
     */
    @ApiModelProperty("菜单编号")
    private Long id;

    /**
     * 父id
     */
    @ApiModelProperty("父id")
    private Long pid;

    /**
     * 菜单编码
     */
    @ApiModelProperty("菜单编码")
    private String menuCode;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String menuName;

    /**
     * 菜单URL
     */
    @ApiModelProperty("菜单URL")
    private String url;
    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;

    /**
     * 序号
     */
    @ApiModelProperty("序号")
    private String number;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 删除标识
     */
    @ApiModelProperty("删除标识")
    private Short yn;

    /**
     * 父菜单
     */
    private MenuVo parentMenu;

    /**
     * 子菜单
     */
    private List<MenuVo> subMenu;

    private boolean hasMenu = false;

}
