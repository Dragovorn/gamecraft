package com.dragovorn.gamecraft.util;

public class StringUtil {

    public static String formatClassPath(Class<?> clazz) {
        return clazz.getCanonicalName().replaceAll("\\.", "/");
    }
}