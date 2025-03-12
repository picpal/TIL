package com.picpal.sandbox.designPattern.FactoryPattern;

public class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza() {
        name = "뉴욕 스타일 소스와 치즈 피자";
        dough = "씬 크러스트 도우";
        sauce = "마리나라 소스";
    }

    @Override
    void cut() {
        System.out.println("삼각형 모양으로 자르는 중...");
    }
}

