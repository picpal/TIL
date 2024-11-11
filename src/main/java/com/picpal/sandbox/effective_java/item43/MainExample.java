package com.picpal.sandbox.effective_java.item43;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainExample {
    public static void main(String[] args) {
        // 1. 정적 메서드 참조
        ComparatorExamples.staticMethodReference();
        // 2. 인스턴스 메서드 참조
        ComparatorExamples.instanceMethodReference();
        // 3. 특정 타입의 인스턴스 메서드 참조
        ComparatorExamples.specificTypeMethodReference();
        // 4. 생성자 참조
        ComparatorExamples.constructorReference();
    }
}