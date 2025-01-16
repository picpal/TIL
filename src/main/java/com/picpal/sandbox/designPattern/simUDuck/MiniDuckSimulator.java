package com.picpal.sandbox.designPattern.simUDuck;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.display();
        mallard.performFly();
        mallard.performQuack();

        Duck model = new ModelDuck();
        model.display();
        model.performFly(); // 날수없음
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
