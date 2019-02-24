package org.tc.provider.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.tc.core.mybatis.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户权限
 */
@Data
@Table(name = "t_action")
public class UserAction extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3132095375636704805L;


    /**
     * 权限名称
     */
    @Column(name = "action_name")
    private String actionName;

    /**
     * 父权限id
     */
    @Column(name = "parent_action_id")
    private Long pid;

    /**
     * 权限描述
     */
    @Column(name = "action_desc")
    private String actionDesc;

    /**
     * 权限类型
     */
    @Column(name = "action_type")
    private Integer actionType;

    /**
     * 权限编码
     */
    @Column(name = "action")
    private String actionCode;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "created_time")
    private Date createdTime;


    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "updated_time")
    private Date updatedTime;


    public UserAction() {
    }

    public UserAction(Long id, Long pid, String actionName, Integer actionType, String actionDesc,
                      Date createdTime, Date updatedTime, String action) {
        this.setId(id);
        this.setPid(pid);
        this.setActionDesc(actionDesc);
        this.setActionName(actionName);
        this.setActionType(actionType);
        this.setActionCode(action);
        this.setCreatedTime(createdTime);
        this.setUpdatedTime(updatedTime);
    }
}