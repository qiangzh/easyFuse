package com.maodr.framework.util;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 *  获取ServletContext、ApplicationContext等信息
 *  @author Administrator
 *  @created 2014年2月15日 上午8:12:26
 *  @lastModified       
 *  @history
 */
public final class ApplicationContextProvider {

    private static ServletContext servletContext = null; // servletContext

    private static ApplicationContext applicationContext = null; // ApplicationContext

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        }
        return applicationContext;
    }

    public static final Object getBean(String className) {
        if (applicationContext == null) {
            throw new NullPointerException("the context of Application is not set !");
        }
        return applicationContext.getBean(className);
    }

    public static final Object getBean(String className, Class clazz) {
        if (applicationContext == null) {
            throw new NullPointerException("the context of Application is not set !");
        }
        return applicationContext.getBean(className, clazz);
    }

    public static final String getRealPath(String path) {
        String realpath = ((WebApplicationContext) applicationContext).getServletContext().getRealPath(path);
        if (!realpath.endsWith("\\") && !realpath.endsWith("/")) {
            realpath = realpath + File.separator;
        }

        return realpath;
    }

    public static final String getRealPath() {
        return getRealPath("/");
    }

    public static void setServletContext(ServletContext servletContext) {
        ApplicationContextProvider.servletContext = servletContext;
        getApplicationContext();
    }

    public static ServletContext getServletContext() {
        getApplicationContext();
        if (applicationContext != null) {
            ServletContext sc = ((WebApplicationContext) applicationContext).getServletContext();
            if (sc != null)
                return sc;
            if (servletContext != null) {
                applicationContext = null;
                getApplicationContext();
            }
        }
        return servletContext;
    }

    public static void destory() {
        servletContext = null;
        applicationContext = null;
    }
}
