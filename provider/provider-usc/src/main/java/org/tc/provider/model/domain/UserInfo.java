package org.tc.provider.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.tc.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_user")
public class UserInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 45958282828932982L;


    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;
    /**
     * 登录密码
     */
    @Column(name = "login_pwd")
    private String loginPwd;

    /**
     * 输错密码次数
     */
    @Column(name = "error_times")
    private Integer errorTimes;

    /**
     * 最后一次输错密码时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "lastpwd_error_time")
    private Date lastErrorTime;

    /**
     * 姓名
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 手机号
     */
    @Column(name = "mobile_no")
    private String mobileNo;
    /**
     * 身份证
     */
    @Column(name = "id_card")
    private String idCard;
    /**
     * 组类型
     */
    @Column(name = "group_type")
    private String groupType;
    /**
     * 组电话
     */
    @Column(name = "group_tele")
    private String groupTele;

    /**
     * 组编号
     */
    @Column(name = "group_code")
    private Long groupCode;

    /**
     * 组名称
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;
    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 激活码
     */
    @Column(name = "active_code")
    private String activeCode;

    /**
     * 激活时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "activated_time")
    private Date activatedTime;

    /**
     * 注册时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "regist_time")
    private Date registTime;

    /**
     * 权限
     */
    @Column(name = "permissions")
    private String permissions;


}
