package com.maodr.framework.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.maodr.framework.util.ApplicationUtil;

/**
 * 
 *  BaseServlet
 *  @author Administrator
 *  @created 2014年1月19日 上午9:18:03
 *  @lastModified       
 *  @history
 */
public class BaseServlet extends HttpServlet {

    private static final String SERVLET_PLUGIN_NAME = new String("plug-in");

    /**
     *  Init plugin
     *  @throws ServletException
     *  @author Administrator
     *  @created 2014年1月19日 上午9:14:46
     *  @lastModified      
     *  @history
     */
    public void init() throws ServletException {
        System.out.println("\n>>Initializing servlet!\n");
        String names = getServletConfig().getInitParameter(SERVLET_PLUGIN_NAME);
        String[] pluginNameArray = names.split("\\s*,\\s*");
        BaseServletPlugIn tbServletPluginArray[] = new BaseServletPlugIn[pluginNameArray.length];
        for (int i = 0; i < pluginNameArray.length; i++) {
            String pluginName = pluginNameArray[i];
            if (pluginName != null && !pluginName.trim().isEmpty()) {
                try {
                    System.out.println(">>    Initializing plugin:" + pluginName.substring(pluginName.lastIndexOf(".") + 1));
                    BaseServletPlugIn plugin = (BaseServletPlugIn) ApplicationUtil.applicationInstance(pluginName);
                    tbServletPluginArray[i] = plugin;
                    plugin.init(this);
                }
                catch (Throwable e) {
                    System.out.println("Init Servlet plugin error, pluginName is:" + pluginName);
                    e.printStackTrace();
                }
            }
        }
        if (tbServletPluginArray != null && tbServletPluginArray.length != 0) {
            getServletContext().setAttribute(SERVLET_PLUGIN_NAME, tbServletPluginArray);

        }

        System.out.println("\n<-------------------system end initialization------------------>");
    }

    /**
     * 
     *  Destory plugin
     *  @author Administrator
     *  @created 2014年1月19日 上午9:14:21
     *  @lastModified      
     *  @history
     */
    public void destroy() {
        BaseServletPlugIn[] tbServletPluginArray = (BaseServletPlugIn[]) getServletContext().getAttribute(
                SERVLET_PLUGIN_NAME);
        for (int i = 0; i < tbServletPluginArray.length; i++) {
            tbServletPluginArray[i].destroy();
        }
        getServletContext().removeAttribute(SERVLET_PLUGIN_NAME);
    }

}
