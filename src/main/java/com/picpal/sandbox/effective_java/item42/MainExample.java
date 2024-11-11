package com.picpal.sandbox.effective_java.item42;

import java.util.*;

public class MainExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        // 익명 클래스 사용 예시
        System.out.println("Using Anonymous Class:");
        ComparatorExamples.sortUsingAnonymousClass(words);
        System.out.println(words);

        // 람다 표현식 사용 예시
        System.out.println("Using Lambda Expression:");
        ComparatorExamples.sortUsingLambda(words);
        System.out.println(words);

        // 메서드 참조 사용 예시
        System.out.println("Using Method Reference:");
        ComparatorExamples.sortUsingMethodReference(words);
        System.out.println(words);
    }
}