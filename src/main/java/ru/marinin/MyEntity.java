package ru.marinin;

import ru.marinin.annotations.ToString;
import ru.marinin.annotations.Invoke;
import ru.marinin.annotations.Validate;

@Validate(MyEntityTests.class)
@ToString(NeedToString.NO)
public class MyEntity extends Entity {
    private String str1 = "s1";
    private int ent1 = 1;

    @Invoke
    public int getEnt1() {
        return ent1;
    }
    @Invoke
    public String getStr1() {
        return str1;
    }
    @Invoke
    private int getNine() {
        return 9;
    }

    private int getTen() {
        return 10;
    }
    @Invoke
    private String getStr2(int x, String str) {
        return "asd";
    }
    @Invoke
    private void getNothing() {
        System.out.println("im here");
    }
    @Invoke
    public static int getStaticInt() {
        return 999;
    }

    @Invoke
    public static String getStaticString(int x) {
        return "static String";
    }
}
