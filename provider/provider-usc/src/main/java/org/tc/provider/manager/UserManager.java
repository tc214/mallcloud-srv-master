package org.tc.provider.manager;

import org.tc.provider.mapper.UserInfoMapper;
import org.tc.provider.model.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户管理
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class UserManager {
    @Resource
    private UserInfoMapper userInfoMapper;


    public void saveUserInfo(final UserInfo uscUser) {
        log.info("保存用户信息.user={}", uscUser);
        userInfoMapper.insertSelective(uscUser);
    }

    public void deleteUserInfo(final UserInfo userInfo) {
        log.info("删除账号.user={}", userInfo);
        userInfoMapper.delete(userInfo);
    }

    public int deleteUserInfoById(final Long userId) {
        log.info("删除账号.userId={}", userId);
        return userInfoMapper.deleteByPrimaryKey(userId);
    }

    public void updateUserInfo(final UserInfo uscUser) {
        log.info("激活用户.user={}", uscUser);
        userInfoMapper.updateByPrimaryKeySelective(uscUser);
    }

    public UserInfo getUserInfoByLoginName(final String name) {
        log.info("登录", name);
        return userInfoMapper.findByLoginName(name);
    }

    public UserInfo getUserInfoById(final Long id) {
        log.info("用户id", id);
        return userInfoMapper.selectByPrimaryKey(id);
    }

    public void resetLoginPwd(UserInfo update, Map<String, Object> map) {
        int updateResult = userInfoMapper.updateByPrimaryKeySelective(update);
        if (updateResult < 1) {
            log.error("用户{}重置密码失败", update.getLoginName());
        } else {
            log.info("用户{}重置密码失败", update.getLoginName());
        }
    }

}
