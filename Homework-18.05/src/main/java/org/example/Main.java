package org.example;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        MessageSource ms = MessageSource.getInstance();

        String welcomeRu = ms.getMessage("WELCOME", new Locale("ru"));
        System.out.println(welcomeRu);

        String welcomeEn = ms.getMessage("WELCOME", Locale.ENGLISH);
        System.out.println(welcomeEn);

        String greeting = ms.getMessage("GREETING", Locale.ENGLISH, "Anvar", 5);
        System.out.println(greeting);

        MessageSource ms2 = MessageSource.getInstance();
        System.out.println("Один и тот же объект: " + (ms == ms2));
    }
}
