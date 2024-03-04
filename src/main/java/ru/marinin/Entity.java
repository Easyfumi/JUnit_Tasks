package ru.marinin;

import ru.marinin.annotations.ToString;
import ru.marinin.annotations.Two;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Entity {

    @Two
    @Override
    public final String toString() {

        List<Field> list = new ArrayList<>(reflectionUtils.feildCollection(this));

        Map<String, Object > fieldsMap = new HashMap<>();


        for (Field f : list) {

            ToString toString = f.getDeclaredAnnotation(ToString.class);

            if (toString==null) {
                toString = f.getDeclaringClass().getDeclaredAnnotation(ToString.class);
            }

            if (!(toString==null||toString.value()==NeedToString.YES)) continue;

            if(Modifier.isStatic(f.getModifiers())) continue;

            try {
                f.setAccessible(true);
                fieldsMap.put(f.getName(), f.get(this));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

    return this.getClass().getSimpleName() + fieldsMap;
    }
}
