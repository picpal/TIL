package com.picpal.sandbox.designPattern.simUDuck;

public class ModelDuck extends Duck{
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("모형 오리 🦃");
    }
}
