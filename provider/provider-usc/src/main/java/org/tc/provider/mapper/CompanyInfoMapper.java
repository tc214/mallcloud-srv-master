package org.tc.provider.mapper;

import org.tc.core.mybatis.MyMapper;
import org.tc.provider.model.domain.CompanyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CompanyInfoMapper extends MyMapper<CompanyInfo> {


}
