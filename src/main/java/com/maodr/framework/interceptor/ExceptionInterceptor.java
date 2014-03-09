package com.maodr.framework.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maodr.framework.base.action.BaseAction;
import com.maodr.framework.exception.BusinessException;
import com.maodr.framework.exception.ExceptionHandle;
import com.maodr.framework.util.StringUtil;
import com.opensymphony.xwork2.ActionInvocation;
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

    protected final transient Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String result = "exception";
        try {
            result = invocation.invoke();
        }
        catch (BusinessException e) {
            String message = ExceptionHandle.handleException(e);

            BaseAction action = (BaseAction) invocation.getAction();
            action.addActionError(message);
            if (!StringUtil.isEmpty(action.getBackUrl())) {
                result = action.getBackUrl();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}