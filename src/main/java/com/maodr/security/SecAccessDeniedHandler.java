package com.maodr.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 
 *  可参考AccessDeniedHandlerImpl
 *  @author Administrator
 *  @created 2014年1月17日 上午6:53:03
 *  @lastModified       
 *  @history
 */
public class SecAccessDeniedHandler implements AccessDeniedHandler {

    private String errorPage;

    public void setErrorPage(String errorPage) {
        if ((errorPage != null) && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage must begin with '/'");
        }

        this.errorPage = errorPage;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
            throws IOException, ServletException {
        if (!response.isCommitted()) {
            boolean isAjax = isAjaxRequest(request);
            System.out.println("是否是ajax请求：" + isAjax);

            if (!isAjax) {
                request.setAttribute("isAjaxRequest", isAjax);
                request.setAttribute("message", ex.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/error/error.jsp");
                dispatcher.forward(request, response);
            }
            else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write("权限不足");
                response.getWriter().close();
            }
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
    }

}