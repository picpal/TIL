package com.picpal.sandbox.designPattern.FactoryPattern;

public class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "시카고 스타일 딥 디쉬 치즈 피자";
        dough = "두꺼운 크러스트 도우";
        sauce = "플럼 토마토 소스";
    }

    @Override
    void cut() {
        System.out.println("네모난 모양으로 자르는 중...");
    }
}