package com.maodr.framework.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maodr.framework.Constants;

public class UserContext {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private String contextPath = null;

    protected static ThreadLocal threadLocal = new ThreadLocal();

    static public void setCurrentContext(HttpServletRequest request, HttpServletResponse response) {
        UserContext us = getCurrentContext();
        if (us == null) {
            us = new UserContext();
            threadLocal.set(us);
        }
        us.setRequest(request);
        us.setResponse(response);
        us.contextPath = request.getContextPath();
    }

    public List getMenuList() {
        List menuList = (List) this.getSessionAttribute(Constants.MENU_REPOSITORY_KEY);
        if (menuList == null) {
            menuList = new ArrayList();
        }
        return menuList;
    }

    public void setMenuList(List menuList) {
        this.setSessionAttribute(Constants.MENU_REPOSITORY_KEY, (Serializable) menuList);
    }

    static public UserContext getCurrentContext() {
        return (UserContext) threadLocal.get();
    }

    protected UserContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        contextPath = request.getContextPath();
    }

    protected UserContext() {
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public ServletContext getServletContext() {
        HttpSession session = this.getSession();
        return session.getServletContext();
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    public Serializable getSessionAttribute(String attr) {
        HttpSession session = this.getSession();
        Object obj = session.getAttribute(attr);
        if (obj == null) {
            return null;
        }
        if (Serializable.class.isAssignableFrom(obj.getClass())) {
            return (Serializable) obj;
        }
        else {
            throw new RuntimeException("Not a serializable object, cann't put in session attribute.");
        }
    }

    public void setSessionAttribute(String attr, Serializable value) {
        HttpSession session = this.getSession();
        session.setAttribute(attr, value);
    }

    public Object getContextAttribute(String attr) {
        ServletContext sc = this.getServletContext();
        return sc.getAttribute(attr);
    }

    public void setContextAttribute(String attr, Object value) {
        ServletContext sc = this.getServletContext();
        sc.setAttribute(attr, value);
    }

    public Object getRequestAttribute(String attr) {
        return request.getAttribute(attr);
    }

    public void setRequestAttribute(String attr, Object value) {
        request.setAttribute(attr, value);
    }

    public String getContextPath() {
        return contextPath;
    }
}
