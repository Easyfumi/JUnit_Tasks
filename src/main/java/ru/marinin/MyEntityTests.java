package ru.marinin;

import ru.marinin.annotations.Test;

public class MyEntityTests {
    @Test
    void test1(MyEntity myEntity) {
        if (myEntity.getEnt1()!=1) throw new RuntimeException("getEnt != 1");
    }
    @Test
    void test2(MyEntity myEntity) {
        if (!myEntity.getStr1().equals("s1")) throw new RuntimeException("getStr != s1");
    }
}
