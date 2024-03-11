package ru.marinin.reflection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.marinin.Entity;
import ru.marinin.NeedToString;
import ru.marinin.annotations.ToString;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;



public class EntityTest {

    EntityOne entityOne = new EntityOne();
    EntityTwo entityTwo = new EntityTwo();
    EntityThree entityThree = new EntityThree();
    EntityFour entityFour = new EntityFour();


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
    @DisplayName("is first is class name?")
    void isEntityCorrectOut() {
        Assertions.assertTrue(entityOne.toString().startsWith("EntityOne"), "toString should start with class name");
    }

    @Test
    @DisplayName("is toString see all fields?")
    void isEntityFindAllFields() {
        Assertions.assertTrue(entityTwo.toString().contains("str2=str 2"), "toString don't have all fields");
        Assertions.assertTrue(entityTwo.toString().contains("count1=1"), "toString don't have all fields");
        Assertions.assertTrue(entityTwo.toString().contains("count2=2"), "toString don't have all fields");
        Assertions.assertTrue(entityTwo.toString().contains("str1=str 1"), "toString don't have all fields");
    }

    @Test
    @DisplayName("@ToString(NeedToString.NO) work correct on class?")
    void isAnnotationNoWorkOnClass() {
        Assertions.assertTrue(entityThree.toString().contains("str1=str 1"), "should see more fields...");
        Assertions.assertTrue(entityThree.toString().contains("count1=1"), "should see more fields...");
        Assertions.assertTrue(!entityThree.toString().contains("str3=str 3"), "annotation 'NO' doesn't work");
        Assertions.assertTrue(!entityThree.toString().contains("count3=3"), "annotation 'NO' doesn't work");
    }

    @Test
    @DisplayName("@ToString(NeedToString.NO) work correct on field?")
    void isAnnotationNoWorkOnField() {
        Assertions.assertTrue(entityFour.toString().contains("str1=str 1"), "should see more fields...");
        Assertions.assertTrue(entityFour.toString().contains("count1=1"), "should see more fields...");
        Assertions.assertTrue(!entityFour.toString().contains("str4=str 4"), "annotation 'NO' doesn't work");
        Assertions.assertTrue(entityFour.toString().contains("count4=4"), "annotation 'NO' doesn't work");
    }


}

class EntityOne extends Entity {
    private String str1 = "str 1";
    public int count1 = 1;
}

class EntityTwo extends EntityOne {
    private String str2 = "str 2";
    public int count2 = 2;
}
@ToString(NeedToString.NO)
class EntityThree extends EntityOne {
    private String str3 = "str 3";
    public int count3 = 3;
}



class EntityFour extends EntityOne {
    @ToString(NeedToString.NO)  private String str4 = "str 4";
    public int count4 = 4;
}