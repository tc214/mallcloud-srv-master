package org.tc.provider.model.domain;

import org.tc.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;

/**
 * The class User menu.
 */
@EqualsAndHashCode(callSuper = true)
@Data
//@Table(name = "t_usc_menu")
public class UserMenu extends BaseEntity {
    private static final long serialVersionUID = 454644589405700059L;

    /**
     * 菜单编码
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 状态
     */
    private String status;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 层级(最多三级1,2,3)
     */
    private Integer level;

    /**
     * 是否叶子节点,1不是0是
     */
    private Integer leaf;

    /**
     * 序号
     */
    private Integer number;

    /**
     * 备注
     */
    private String remark;
    /**
     * 系统ID
     */
    @Column(name = "application_id")
    private Long applicationId;
}