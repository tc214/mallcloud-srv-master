package org.tc.provider.mapper;

import org.tc.core.mybatis.MyMapper;
import org.tc.provider.model.domain.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface UserTokenMapper extends MyMapper<UserToken> {

    List<UserToken> selectTokenList(UserToken userToken);

    int batchUpdateTokenOffLine(Map<String, Object> map);

    List<Long> listOffLineTokenId();
}