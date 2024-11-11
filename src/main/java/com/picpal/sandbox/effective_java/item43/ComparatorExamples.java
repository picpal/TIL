package com.picpal.sandbox.effective_java.item43;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 람다 대비 메서드 참조 사용 장점
 * 1) 더 직관적
 * 2) 간단한 호출 방식
 * 3) 의도의 명확성
 *
 * 메서드 참조 방법 4가지
 * 1) 정적 메서드
 * 2) 인스턴스 메서드
 * 3) 특정 타입
 * 4) 생성자
 * */
public class ComparatorExamples {

    // 1. 정적 메서드 참조
    public static void staticMethodReference() {
        // 정적 메서드 참조 (Integer::parseInt)
        Function<String, Integer> parseInt = Integer::parseInt;
        Integer result = parseInt.apply("123");
        System.out.println("Static Method Reference: " + result); // 출력: 123
    }

    // 2. 인스턴스 메서드 참조
    public static void instanceMethodReference() {
        // 인스턴스 메서드 참조 (System.out::println)
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(System.out::println);
    }

    // 3. 특정 타입의 인스턴스 메서드 참조
    public static void specificTypeMethodReference() {
        // 특정 타입의 인스턴스 메서드 참조 (String::toUpperCase)
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        List<String> upperCaseWords = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Specific Type Method Reference: " + upperCaseWords);
    }

    // 4. 생성자 참조
    public static void constructorReference() {
        // 생성자 참조 (ArrayList::new)
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> list = listSupplier.get();
        list.add("Hello");
        list.add("World");
        System.out.println("Constructor Reference: " + list);
    }

}