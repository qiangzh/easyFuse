package com.maodr.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.maodr.security.tool.AntUrlPathMatcher;
import com.maodr.security.tool.UrlMatcher;

/** 
 *  
 * 此类在初始化时，应该取到所有资源及其对应角色的定义 
 *  
 */

/**
 * 
 *  加载系统资源与权限列表
 *  @author Administrator
 *  @created 2014年1月11日 下午6:39:37
 *  @lastModified       
 *  @history
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public MyInvocationSecurityMetadataSource() {
        loadResourceDefine();
    }

    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        ConfigAttribute ca = new SecurityConfig("ROLE_USER");
        atts.add(ca);
        resourceMap.put("/index.jsp", atts);

        Collection<ConfigAttribute> attsno = new ArrayList<ConfigAttribute>();
        ConfigAttribute cano = new SecurityConfig("ROLE_NO");
        attsno.add(cano);
        resourceMap.put("/other.jsp", attsno);
    }

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // TODO 
        //        String url = ((FilterInvocation) object).getRequestUrl();
        //        Iterator<String> ite = resourceMap.keySet().iterator();
        //        while (ite.hasNext()) {
        //            String resURL = ite.next();
        //            if (urlMatcher.pathMatchesUrl(resURL, url)) {
        //                return resourceMap.get(resURL);
        //            }
        //        }
        //        return null;

        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        ConfigAttribute ca = new SecurityConfig("ROLE_USER");
        atts.add(ca);
        return atts;

    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

}