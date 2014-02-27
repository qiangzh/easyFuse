package com.maodr.framework;

public final class Constants {

    private Constants() {
    }

    public static final String MENU_REPOSITORY_KEY = "USER_MENU_REPOSITORY";

    public static final String FILE_SEP = System.getProperty("file.separator");

    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

}
