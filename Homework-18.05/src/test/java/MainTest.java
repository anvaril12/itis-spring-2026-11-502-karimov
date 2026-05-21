package org.example;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void getInstance_returnsSameObject() {
        MessageSource ms1 = MessageSource.getInstance();
        MessageSource ms2 = MessageSource.getInstance();
        assertSame(ms1, ms2);
    }

    @Test
    void getMessage_english_welcome() {
        MessageSource ms = MessageSource.getInstance();
        String result = ms.getMessage("WELCOME", Locale.ENGLISH);
        assertEquals("Welcome to my program", result);
    }

    @Test
    void getMessage_english_greeting_withArgs() {
        MessageSource ms = MessageSource.getInstance();
        String result = ms.getMessage("GREETING", Locale.ENGLISH, "Anvar", 5);
        assertEquals("Hello, Anvar! You have 5 new messages.", result);
    }


    @Test
    void getMessage_russian_welcome() {
        MessageSource ms = MessageSource.getInstance();
        String result = ms.getMessage("WELCOME", new Locale("ru"));
        assertEquals("Добро пожаловать в мою программу", result);
    }

    @Test
    void getMessage_russian_greeting_withArgs() {
        MessageSource ms = MessageSource.getInstance();
        String result = ms.getMessage("GREETING", new Locale("ru"), "Иван", 3);
        assertEquals("Привет, Иван! У вас 3 новых сообщений.", result);
    }

    @Test
    void getMessage_noArgs_returnsTemplateAsIs() {
        MessageSource ms = MessageSource.getInstance();
        String result = ms.getMessage("WELCOME", Locale.ENGLISH);
        assertFalse(result.contains("{0}"));
    }

    @Test
    void getMessage_multipleArgs_allSubstituted() {
        MessageSource ms = MessageSource.getInstance();
        String result = ms.getMessage("GREETING", Locale.ENGLISH, "Bob", 42);
        assertTrue(result.contains("Bob"));
        assertTrue(result.contains("42"));
        assertFalse(result.contains("{0}"));
        assertFalse(result.contains("{1}"));
    }

    @Test
    void getMessage_unsupportedLocale_fallbackToEnglish() {
        MessageSource ms = MessageSource.getInstance();
        String result = ms.getMessage("WELCOME", Locale.GERMAN);
        assertEquals("Welcome to my program", result);
    }

    @Test
    void getMessage_unknownKey_throwsIllegalArgumentException() {
        MessageSource ms = MessageSource.getInstance();
        assertThrows(IllegalArgumentException.class, () ->
                ms.getMessage("NO_SUCH_KEY", Locale.ENGLISH)
        );
    }
}