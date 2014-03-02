package com.maodr.framework.interceptor;

import com.maodr.framework.exception.BusinessException;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 *  异常拦截器
 *  @author Administrator
 *  @created 2014年3月2日 下午3:15:39
 *  @lastModified       
 *  @history
 */

public class ExceptionInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -297643788734048505L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String result = "exception";

        try {
            result = invocation.invoke();
        }
        catch (BusinessException e) {
            e.printStackTrace();
            
            ActionSupport action = (ActionSupport) invocation.getAction();
            action.addActionError(e.getMessage());
        }
        return result;
    }
}