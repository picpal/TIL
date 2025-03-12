package com.picpal.sandbox.designPattern.DecoratorPattern;

public abstract class CondimentDecorator extends Beverage{
    Beverage beverage;
    public abstract String getDescription();

    public Size getSize() {
        return beverage.getSize();
    }
}
