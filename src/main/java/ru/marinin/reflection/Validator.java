package ru.marinin.reflection;

import ru.marinin.reflection.annotations.Test;
import ru.marinin.reflection.annotations.Validate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Validator {
    public static void validate(Object... objectForTest){
        for (Object object : objectForTest) {
            try {
                Class[] testClasses = object.getClass()
                        .getDeclaredAnnotation(Validate.class)
                        .value();
                for (Class testClass : testClasses) {
                    Constructor cstr = testClass.getDeclaredConstructor();
                    cstr.setAccessible(true);
                    Object testerObject = cstr.newInstance();
                    Method[] methods = testClass.getDeclaredMethods();
                    for (Method test : methods) {
                        if (!test.isAnnotationPresent(Test.class)) continue;
                        System.out.println(test);
                        test.setAccessible(true);
                        test.invoke(testerObject, object);
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
        }
    }
}
