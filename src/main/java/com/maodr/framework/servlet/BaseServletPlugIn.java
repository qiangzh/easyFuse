package com.maodr.framework.servlet;

import javax.servlet.ServletException;

/**
 * 
 *  plugin Interface
 *  @author Administrator
 *  @created 2014年1月19日 上午9:15:27
 *  @lastModified       
 *  @history
 */
public interface BaseServletPlugIn {

    void destroy();

    void init(BaseServlet servlet) throws ServletException;
}
