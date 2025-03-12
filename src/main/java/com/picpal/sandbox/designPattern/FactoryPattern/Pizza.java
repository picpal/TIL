package com.picpal.sandbox.designPattern.FactoryPattern;

public abstract class Pizza {
    String name;
    String dough;
    String sauce;

    void prepare() {
        System.out.println("준비 중: " + name);
    }

    void bake() {
        System.out.println("굽는 중...");
    }

    void cut() {
        System.out.println("자르는 중...");
    }

    void box() {
        System.out.println("포장 중...");
    }

    public String getName() {
        return name;
    }
}
