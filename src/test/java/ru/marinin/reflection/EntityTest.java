package ru.marinin.reflection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.marinin.Entity;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;



public class EntityTest {
    @Test
    @DisplayName("is ToString FINAL?")
    void isFinal() {
        try {
            Method method = Entity.class.getMethod("toString");
            String[] strings = method.toString().split(" ");
            Assertions.assertTrue(strings[1].equals("final"), "toSting should be FINAL");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("is Entity ABSTRACT?")
    void isEntityAbstract() {
        Assertions.assertTrue(Modifier.isAbstract(Entity.class.getModifiers()), "Entity should be abstract class");
    }

    @Test
    @DisplayName("is toString return String?")
    void isReturnString() {
        try {
            Method method = Entity.class.getMethod("toString");
            Assertions.assertTrue(method.getReturnType()==String.class, "Returning type should be String");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    // Почему он не видит аннотацию Override?

//    @Test
//    @DisplayName("is toString has Override annotation?")
//    void overrideAnnotationCheck() {
//        try {
//            Method method = Entity.class.getMethod("toString");
//             var x = method.getDeclaredAnnotations();
//            System.out.println(Arrays.asList(x));
//            Assertions.assertTrue(method.getDeclaredAnnotation(Override.class)!=null, "toString should have Override annotation");
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
