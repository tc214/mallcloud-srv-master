package org.tc.provider.mapper;

import org.tc.core.mybatis.MyMapper;
import org.tc.provider.model.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface UserInfoMapper extends MyMapper<UserInfo> {

    // 自定义方法，通过SQL实现
    UserInfo findByLoginName(String loginName);

    UserInfo findByLoginNameAndLoginPwd(Map<String, String> loginNamePwdMap);

    List<UserInfo> selectUserList(UserInfo userInfo);

    UserInfo selectUserInfoByUserId(Long userId);

    int updateUserInfo(UserInfo userInfo);

    UserInfo findUserInfoByLoginName(@Param("loginName") String loginName);
}
