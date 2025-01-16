package com.picpal.sandbox.designPattern.simUDuck;

public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("날수 있음 :)");
    }
}
