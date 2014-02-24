package com.maodr.framework.util;

public class ApplicationUtil {

    public static final Class applicationClass(String className) throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = ApplicationUtil.class.getClassLoader();
        }
        return classLoader.loadClass(className);
    }

    public static final Object applicationInstance(String className) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        return applicationClass(className).newInstance();
    }

}
