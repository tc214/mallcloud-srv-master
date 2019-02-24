package org.tc.provider.security;

import org.tc.provider.model.domain.UserInfo;
import org.tc.provider.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService uscUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> grantedAuthorities;
        UserInfo user = uscUserService.findByLoginName(username);
        if (user == null) {
            throw new BadCredentialsException("用户名不存在或者密码错误");
        }
        user = uscUserService.findUserInfoByUserId(user.getId());
        user.setStatus("ENABLE");//todo
        grantedAuthorities = uscUserService.loadUserAuthorities(user.getId());
        return new SecurityUser(user.getId(), user.getLoginName(), user.getLoginPwd(),
                user.getUserName(), user.getGroupCode(), user.getGroupName(), user.getStatus(), grantedAuthorities);

    }


}
