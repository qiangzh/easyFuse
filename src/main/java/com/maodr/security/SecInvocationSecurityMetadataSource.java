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
 *  从数据库提取权限和资源，装配到HashMap中
 *  @author Administrator
 *  @created 2014年1月11日 下午6:39:37
 *  @lastModified       
 *  @historya
 */
public class SecInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public SecInvocationSecurityMetadataSource() {
        loadResourceDefine();
    }

    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        // TODO 加载资源、权限对应关系
        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        ConfigAttribute ca = new SecurityConfig("ROLE_USER");
        atts.add(ca);
        resourceMap.put("/home", atts);
    }

    /**
     *  获取资源对应的权限列表
     *  @param object
     *  @return
     *  @throws IllegalArgumentException
     *  @author Administrator
     *  @created 2014年1月14日 上午6:26:31
     *  @lastModified      
     *  @history
     */
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // TODO 此处实现效率有些低下，需要完善,根据url获取resURl
        String url = ((FilterInvocation) object).getRequestUrl();
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (urlMatcher.pathMatchesUrl(resURL, url)) {
                return resourceMap.get(resURL);
            }
        }
        return null;

    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

}