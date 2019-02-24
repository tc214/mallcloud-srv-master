package org.tc.provider.model.domain;


import org.tc.core.mybatis.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Table(name = "t_company")
public class CompanyInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 6623474350416570497L;


    /**
     * 单位名
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 单位编码
     */
    @Column(name = "group_code")
    private String groupCode;

    @Column(name = "group_type")
    private String groupType;

    @Column(name = "liscense_num")
    private String liscenseNum;

    @Column(name = "group_tele")
    private String groupTele;

    @Column(name = "fax")
    private String fax;

    @Column(name = "group_email")
    private String groupEmail;

    @Column(name = "group_address")
    private String groupAddress;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "prj_name")
    private String projectName;

    @Column(name = "prj_contract")
    private String projectContract;

    @Column(name = "prj_start_date")
    private String projectStartDate;

    @Column(name = "prj_end_date")
    private String projectEndDate;

    @Column(name = "status")
    private String status;


}
