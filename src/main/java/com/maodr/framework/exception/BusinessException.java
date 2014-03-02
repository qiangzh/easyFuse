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

    private String key; // 异常代码

    private Object[] values;// 一些其他信息

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

    public BusinessException(String message, String key) {
        super(message);
        this.key = key;
    }

    public BusinessException(String message, String key, Object value) {
        super(message);
        this.key = key;
        this.values = new Object[] { value };
    }

    public BusinessException(String message, String key, Object[] values) {
        super(message);
        this.key = key;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public Object[] getValues() {
        return values;
    }
}