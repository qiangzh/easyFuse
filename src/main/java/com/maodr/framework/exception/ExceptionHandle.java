package com.maodr.framework.exception;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *  业务异常类处理类
 *  @author Administrator
 *  @created 2014年3月2日 下午3:01:05
 *  @lastModified       
 *  @history
 */
public class ExceptionHandle extends RuntimeException {

    private static final long serialVersionUID = -4700200141467787890L;

    protected final static Logger log = LoggerFactory.getLogger(BaseException.class);

    public static String handleException(Throwable e) {
        String message = e.getMessage();
        if (e instanceof BusinessException) {
            Object[] params = ((BusinessException) e).getParams();
            message = MessageFormat.format(message, params);
        }
        log.error(message);
        e.printStackTrace();
        return message;

    }

}