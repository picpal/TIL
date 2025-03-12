package com.picpal.sandbox.designPattern.FactoryPattern;

public abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type); // 팩토리 메소드 호출

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract Pizza createPizza(String type); // 팩토리 메소드
}
