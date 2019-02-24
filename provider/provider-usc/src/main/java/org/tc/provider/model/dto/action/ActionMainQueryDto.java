package org.tc.provider.model.dto.action;

import org.tc.base.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class ActionMainQueryDto extends BaseQuery {
    private static final long serialVersionUID = -1755881173841393763L;
    /**
     * 资源路径
     */
    private String url;

    /**
     * 权限名称
     */
    private String actionName;

    /**
     * 权限编码
     */
    private String actionCode;

    /**
     * 状态
     */
    private String status;

    /**
     * 菜单ID
     */
    private List<Long> menuIdList;
}
