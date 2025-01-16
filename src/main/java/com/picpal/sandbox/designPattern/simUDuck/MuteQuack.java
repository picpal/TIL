package com.picpal.sandbox.designPattern.simUDuck;

public class MuteQuack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("...");
    }
}
