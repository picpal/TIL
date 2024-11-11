package com.picpal.sandbox.effective_java.item42;

import java.util.*;

/**
 * 익명 클래스 대비람다 사용 장점
 * 1) 간견함
 * 2) 가독성
 * 3) 함수형 프로그래밍 지원
 *
 * But, 무조건 람다가 아니라 익명 클래스가 필요한 경우 익명 클래스 사용
 *
 * 익명 클래스 필요한 경우
 * 1) 람다에서 this와 super를 사용할 수 없는 경우
 * 2) 복수의 메서드를 구현해야 할 때
 * 3) 람다 표현식이 적합하지 않은 경우
 * */
public class ComparatorExamples {

    // 익명 클래스를 사용한 문자열 길이 비교
    public static void sortUsingAnonymousClass(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
    }

    // 람다 표현식을 사용한 문자열 길이 비교
    public static void sortUsingLambda(List<String> list) {
        Collections.sort(list, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
    }

    // 메서드 참조를 사용한 문자열 길이 비교 ( 추천 )
    public static void sortUsingMethodReference(List<String> list) {
        list.sort(Comparator.comparingInt(String::length));
    }
}