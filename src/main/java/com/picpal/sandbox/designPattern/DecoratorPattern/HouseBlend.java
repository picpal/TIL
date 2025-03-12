package com.picpal.sandbox.designPattern.DecoratorPattern;

public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "하우스 블렌드 커피";
    }

    public double cost(){
        return 0.89;
    }

}
