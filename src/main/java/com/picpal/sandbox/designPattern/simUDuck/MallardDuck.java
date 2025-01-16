package com.picpal.sandbox.designPattern.simUDuck;

public class MallardDuck extends Duck{
    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("물오리 🦆");
    }
}
