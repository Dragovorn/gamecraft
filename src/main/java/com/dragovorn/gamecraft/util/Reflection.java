package com.dragovorn.gamecraft.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Reflection {

    public static <T> T newInstance(Class<T> clazz, boolean access, Object... params) {
        Constructor<T> constructor = Reflection.getConstructor(clazz, getTypes(params));

        return constructor == null ? null : newInstance(constructor, access, params);
    }

    public static <T> T newInstance(Constructor<T> constructor, boolean access, Object... params) {
        try {
            boolean wasAccess = constructor.isAccessible();

            if (!wasAccess && access) {
                constructor.setAccessible(true);
            }

            T instance = constructor.newInstance(params);

            if (!wasAccess && access) {
                constructor.setAccessible(false);
            }

            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static Class<?>[] getTypes(Object... params) {
        Class<?>[] types = new Class<?>[params.length];

        for (int x = 0; x < params.length; x++) {
            Object param = params[x];
            types[x] = params[x] == null ? Void.class : param.getClass();
        }

        return types;
    }

    @SuppressWarnings({"unchecked"})
    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... params) {
        for (Constructor<?> constructors : clazz.getDeclaredConstructors()) {
            if (constructors.getParameterCount() != params.length) {
                continue;
            }

            if (Reflection.matchParams(constructors.getParameterTypes(), params)) {
                return (Constructor<T>) constructors;
            }
        }

        return clazz.getSuperclass() == null ? null : getConstructor((Class<T>) clazz.getSuperclass(), params);
    }

    public static boolean matchParams(Class<?>[] params, Class<?>... types) {
        for (int x = 0; x < params.length; x++) {
            Class<?> param = params[x];
            Class<?> type = types[x];

            if (!param.isAssignableFrom(type)) {
                if (param.isPrimitive() || type.isPrimitive()) {
                    Class<?> type1 = param.isPrimitive() ? param : Reflection.getValue(param, null, "TYPE");
                    Class<?> type2 = type.isPrimitive() ? type : Reflection.getValue(type, null, "TYPE");

                    if (type1 == type2) {
                        return true;
                    }
                }

                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(Class<?> clazz, Object instance, String name) {
        return Reflection.getValue(Reflection.getField(clazz, name), instance);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(Field field, Object instance) {
        if (field == null) {
            return null;
        }

        boolean access = field.isAccessible();

        if (!access) {
            field.setAccessible(true);
        }

        T value = null;

        try {
            value = (T) field.get(instance);
        } catch (ClassCastException | IllegalAccessException exception) {
            exception.printStackTrace();
        }

        if (!access) {
            field.setAccessible(false);
        }

        return value;
    }

    public static Field getField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException exception) {
            try {
                return clazz.getSuperclass() == null ? null : clazz.getSuperclass().getField(name);
            } catch (NoSuchFieldException e) {
                return null;
            }
        }
    }
}