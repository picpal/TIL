package com.picpal.sandbox.designPattern.DecoratorPattern;

public class Soy extends CondimentDecorator {
    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    public double cost() {
        return beverage.cost() + 0.15;
    }

    public String getDescription() {
        return beverage.getDescription() + ", 두유" ; 
    }
}
