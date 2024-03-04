package ru.marinin.reflection;


import ru.marinin.reflection.annotations.ToString;

public class MyEntityChild extends MyEntity {
    @ToString(NeedToString.NO)
    private String str2 = "s2";
    private int ent2 = 2;
}
