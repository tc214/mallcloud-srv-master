package org.tc.provider.manager;


import org.tc.provider.model.domain.CompanyInfo;
import org.tc.provider.mapper.CompanyInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class CompanyManager {

    @Resource
    CompanyInfoMapper companyInfoMapper;


    public void saveCompanyInfo(CompanyInfo companyInfo) {
        log.info("保存单位信息.companyInfo={}", companyInfo);
        companyInfoMapper.insertSelective(companyInfo);
    }

}
