package com.maodr.framework.util;

public class StringUtil {

    /**
     * 
     *  字符串为空
     *  @param str
     *  @return
     *  @author Administrator
     *  @created 2014年3月4日 上午5:51:54
     *  @lastModified       
     *  @history
     */
    public static final boolean isEmpty(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true;
        }
        return false;
    }

}
