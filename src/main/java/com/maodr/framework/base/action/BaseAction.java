package com.maodr.framework.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 *  BaseActon
 *  @author Administrator
 *  @created 2013年12月18日 下午9:51:22
 *  @lastModified       
 *  @history
 */
public class BaseAction extends ActionSupport {
    private static final long serialVersionUID = 3525445612504421307L;

    /**
     * Transient log to prevent session synchronization issues - children can use instance for logging.
     */
    protected final transient Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Constant for cancel result String
     */
    public static final String CANCEL = "cancel";

    /**
     * Indicator if the user clicked cancel
     */
    protected String cancel;

    /**
     * Indicator for the page the user came from.
     */
    protected String from;

    /**
     * Set to "delete" when a "delete" request parameter is passed in
     */
    protected String delete;

    /**
     * Set to "save" when a "save" request parameter is passed in
     */
    protected String save;

    /**
     * Simple method that returns "cancel" result
     *
     * @return "cancel"
     */
    public String cancel() {
        return CANCEL;
    }

    /**
     * 
     *  获取Request
     *  @return
     *  @author Administrator
     *  @created 2013年12月18日 下午9:53:29
     *  @lastModified       
     *  @history
     */
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    /**
     * 
     *  获取Respose
     *  @return
     *  @author Administrator
     *  @created 2013年12月18日 下午9:55:52
     *  @lastModified       
     *  @history
     */
    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    /**
     * 
     *  获取Session
     *  @return
     *  @author Administrator
     *  @created 2013年12月18日 下午9:56:10
     *  @lastModified       
     *  @history
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 
     *  获取Spring Bean
     *  @param beanName
     *  @return
     *  @author Administrator
     *  @created 2013年12月30日 上午6:35:11
     *  @lastModified       
     *  @history
     */
    public Object getBean(String beanName) {
        return null;
    }
}
