package com.maodr.framework.exception;

/**
 * 
 *  业务异常类
 *  @author Administrator
 *  @created 2014年3月2日 下午3:01:05
 *  @lastModified       
 *  @history
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 2074567259114230490L;

    protected String message;

    protected Throwable cause;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(Throwable e) {
        super(e);
        this.cause = e;
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
        this.message = message;
        this.cause = e;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

}