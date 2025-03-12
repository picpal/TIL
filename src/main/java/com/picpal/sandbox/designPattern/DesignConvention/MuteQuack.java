package com.picpal.sandbox.designPattern.DesignConvention;

public class MuteQuack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("...");
    }
}
