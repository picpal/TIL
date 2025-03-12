package com.picpal.sandbox.designPattern.FactoryPattern;

public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NYStyleCheesePizza();
        }
        // 다른 피자 종류 추가 가능
        return null;
    }
}