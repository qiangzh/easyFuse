package com.maodr.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.maodr.system.user.service.UserService;
import com.maodr.system.user.vo.UserVO;

/**
 * 
 *  验证用户并授权
 *  @author Administrator
 *  @created 2014年1月11日 下午6:37:12
 *  @lastModified       
 *  @history
 */
public class SecUserDetailService implements UserDetailsService {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        // 根据用户名获取用户信息
        UserVO userVO = userService.getUserByUsername(username);
        if (userVO == null) {
            throw new UsernameNotFoundException("cannt find user :" + username);
        }
        
        // 根据用户查询用户权限
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority auth1 = new SimpleGrantedAuthority("ROLE_USER");
        SimpleGrantedAuthority auth2 = new SimpleGrantedAuthority("ROLE_ADMIN");
        SimpleGrantedAuthority auth3 = new SimpleGrantedAuthority("ROLE_ANONYMOUS");        
        auths.add(auth1);
        auths.add(auth2);
        auths.add(auth3);

        // 封装为UserDetails
        UserDetails user = new User(userVO.getUsername(), userVO.getPassword(), auths);
        return user;
    }
}