package com.maodr.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 *  匹配用户拥有权限和请求权限
 *  @author Administrator
 *  @created 2014年1月11日 下午6:34:18
 *  @lastModified       
 *  @history
 */
public class SecAccessDecisionManager implements AccessDecisionManager {

    /**
     *  校验票据是否有访问资源是否有权限
     *  @param authentication 认证过的票据(谁正在访问资源)
     *  @param object 被访问的资源
     *  @param configAttributes 访问资源要求的权限配置
     *  @throws AccessDeniedException
     *  @throws InsufficientAuthenticationException
     *  @author Administrator
     *  @created 2014年1月14日 上午5:47:37
     *  @lastModified      
     *  @history
     */
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // 资源在系统中未进行配置
        System.out.println("URL===" + object.toString());
        if (configAttributes == null || configAttributes.isEmpty()) {
            return; // URL未进行设置,默认允许允许访问
        }

        // 资源在系统中进行了配置
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        while (ite.hasNext()) {
            ConfigAttribute ca = ite.next();
            String needRole = ((SecurityConfig) ca).getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.equals(ga.getAuthority())) {
                    return; // 角色匹配，允许访问
                }
            }
        }
        throw new AccessDeniedException("no right"); // 角色不匹配，拒绝访问
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

}