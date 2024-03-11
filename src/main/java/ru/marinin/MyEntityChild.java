package ru.marinin;


import ru.marinin.annotations.ToString;

public class MyEntityChild extends MyEntity {
    @ToString(NeedToString.YES)
    public String str2 = "s2";
    private int ent2 = 2;
}
