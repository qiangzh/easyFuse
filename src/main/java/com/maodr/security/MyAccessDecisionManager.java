package com.maodr.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

/**
 * 
 *  匹配用户拥有权限和请求权限
 *  @author Administrator
 *  @created 2014年1月11日 下午6:34:18
 *  @lastModified       
 *  @history
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        //        if (configAttributes == null) {
        //            return;
        //        }
        //        System.out.println(object.toString()); //object is a URL. 
        //        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        //        while (ite.hasNext()) {
        //            ConfigAttribute ca = ite.next();
        //            String needRole = ((SecurityConfig) ca).getAttribute();
        //            for (GrantedAuthority ga : authentication.getAuthorities()) {
        //                if (needRole.equals(ga.getAuthority())) { //ga is user's role. 
        //                    return;
        //                }
        //            }
        //        }
        //        throw new AccessDeniedException("no right");
        // TODO 匹配用户拥有权限和请求权限
        return;
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

}