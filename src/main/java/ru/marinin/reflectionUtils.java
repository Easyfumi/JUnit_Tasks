package ru.marinin;

import ru.marinin.annotations.Invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class reflectionUtils {
    public static List feildCollection(Object object) {
        List<Class> list = new ArrayList<>();
        List<Field> fieldList = new ArrayList<>();
        Class tmp = object.getClass();
        list.add(tmp);
        while (!tmp.getSimpleName().equals("Object")) {
            tmp = tmp.getSuperclass();
            list.add(tmp);
        }
        for (Class clz : list) {
            fieldList.addAll(Arrays.asList(clz.getDeclaredFields()));
        }
        return fieldList;
    }

    public static Map collect(Class... classes) {

        Map<Object, Object> map = new HashMap<>();

        try {
            for (Class clz : classes) {
                Constructor constructor = clz.getDeclaredConstructor();
                Object object = constructor.newInstance();
                Method[] methods = object.getClass().getDeclaredMethods();

                for (Method method : methods) {
                    method.setAccessible(true);
                    if (method.isAnnotationPresent(Invoke.class)
                        && method.getParameterCount() == 0
                        && method.getReturnType()!=void.class) {
                        map.put(method.getName(), method.invoke(object));
                    }
                }
            }

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
