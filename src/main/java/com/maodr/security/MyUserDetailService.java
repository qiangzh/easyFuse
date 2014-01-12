package com.maodr.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 
 *  验证用户并授权
 *  @author Administrator
 *  @created 2014年1月11日 下午6:37:12
 *  @lastModified       
 *  @history
 */
public class MyUserDetailService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        GrantedAuthorityImpl auth1 = new GrantedAuthorityImpl("ROLE_USER");
        GrantedAuthorityImpl auth2 = new GrantedAuthorityImpl("ROLE_ADMIN");
        auths.add(auth1);
        auths.add(auth2);

        User user = new User(username, "admin", true, true, true, true, auths);
        return user;
        //  return userDao.loadUserByUsername(username);
    }

}