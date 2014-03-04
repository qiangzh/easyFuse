package com.maodr.framework.exception;


/**
 * 
 *  业务异常类
 *  @author Administrator
 *  @created 2014年3月2日 下午3:01:05
 *  @lastModified       
 *  @history
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2636146773023628669L;   

    private String key; // TODO 异常代码，国际化使用

    private Object[] params;// 参数信息

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String message, Object params) {
        super(message);
        this.params = new Object[] { params };
    }

    public BusinessException(String message, Object[] params) {
        super(message);
        this.params = params;
    }

    public String getKey() {
        return key;
    }

    public Object[] getValues() {
        return params;
    }
}