package org.example;

import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Letter letter = new Letter("Паша", "Тимур", "Привет");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("ObjectToMap:");
        Map<String, Object> map = ObjectMapper.objectToMap(letter);
        System.out.println("Исходный объект: " + letter.getFrom() + ", " + letter.getTo() + ", " + letter.getContent());
        System.out.println("После преобразования в Map: " + map);
        System.out.println();

        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("MapToObject");
        Letter restoredLetter = ObjectMapper.mapToObject(map,Letter.class);
        System.out.println("Из Map обратно в объект: " + restoredLetter.getFrom() + ", " + restoredLetter.getTo() + ", " + restoredLetter.getContent());
        System.out.println();
    }
}