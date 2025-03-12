package com.picpal.sandbox.designPattern.DesignConvention;

public class FlyNoWay implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("날수 없음 :(");
    }
}
