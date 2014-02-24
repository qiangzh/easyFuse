package com.maodr.framework.plugin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.maodr.framework.servlet.BaseServlet;
import com.maodr.framework.servlet.BaseServletPlugIn;
import com.maodr.framework.util.ApplicationContextProvider;

public class WebApplicationContextInitPlugIn implements BaseServletPlugIn {

    public void destroy() {
        ApplicationContextProvider.destory();
    }

    public void init(BaseServlet servlet) throws ServletException {
        ServletContext servletContext = servlet.getServletContext();
        ApplicationContextProvider.setServletContext(servletContext);
    }
}
