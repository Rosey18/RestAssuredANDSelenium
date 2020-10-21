package com.headfirstjava.projects.generics;

import java.util.HashMap;

public class TestMap {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = new HashMap<String, Integer>(); //HashMap нуждается в двух типовых параметров: для ключа и значения(String, Integer)
        scores.put("Katy", 42);
        scores.put("Bert", 343);
        scores.put("Skyler", 420);

        System.out.println(scores);
        System.out.println(scores.get("Bert")); //метод get() принимает ключ и возвращает значение(В данном случае Integer)
    }
}





