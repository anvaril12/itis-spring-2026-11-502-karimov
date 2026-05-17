package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class MainTest {

    private Letter letter;

    @BeforeEach
    void setUp() {
        letter = new Letter("Паша", "Тимур", "Привет");
    }

    @Test
    void testObjectToMap_returnsNotNull() {
        Map<String, Object> map = ObjectMapper.objectToMap(letter);
        assertNotNull(map, "Map не должен быть null");
    }

    @Test
    void testObjectToMap_containsCorrectNumberOfFields() {
        Map<String, Object> map = ObjectMapper.objectToMap(letter);
        assertEquals(3, map.size(), "Должно быть 3 поля: from, to, content");
    }

    @Test
    void testObjectToMap_containsAllExpectedKeys() {
        Map<String, Object> map = ObjectMapper.objectToMap(letter);
        assertTrue(map.containsKey("from"), "Должен быть ключ 'from'");
        assertTrue(map.containsKey("to"), "Должен быть ключ 'to'");
        assertTrue(map.containsKey("content"), "Должен быть ключ 'content'");
    }

    @Test
    void testObjectToMap_hasCorrectValues() {
        Map<String, Object> map = ObjectMapper.objectToMap(letter);
        assertEquals("Паша", map.get("from"));
        assertEquals("Тимур", map.get("to"));
        assertEquals("Привет", map.get("content"));
    }

    @Test
    void testMapToObject_restoresObjectCorrectly() {
        Map<String, Object> map = ObjectMapper.objectToMap(letter);
        Letter restored = ObjectMapper.mapToObject(map, Letter.class);

        assertNotNull(restored);
        assertEquals(letter.getFrom(), restored.getFrom());
        assertEquals(letter.getTo(), restored.getTo());
        assertEquals(letter.getContent(), restored.getContent());
    }

    @Test
    void testFullCycle_objectToMapToObject_maintainsData() {
        Map<String, Object> map = ObjectMapper.objectToMap(letter);
        Letter restored = ObjectMapper.mapToObject(map, Letter.class);

        assertEquals(letter.getFrom(), restored.getFrom());
        assertEquals(letter.getTo(), restored.getTo());
        assertEquals(letter.getContent(), restored.getContent());
    }

    @Test
    void testObjectToMap_withNull_returnsNull() {
        Map<String, Object> map = ObjectMapper.objectToMap(null);
        assertNull(map, "При null должен возвращаться null");
    }

    @Test
    void testMapToObject_withNullMap_returnsNull() {
        Letter result = ObjectMapper.mapToObject(null, Letter.class);
        assertNull(result, "При null map должен возвращаться null");
    }

    @Test
    void testObjectToMap_withEmptyObject() {
        Letter emptyLetter = new Letter();
        Map<String, Object> map = ObjectMapper.objectToMap(emptyLetter);

        assertNotNull(map);
        assertEquals(3, map.size());
        assertNull(map.get("from"));
        assertNull(map.get("to"));
        assertNull(map.get("content"));
    }

    @Test
    void testWithDifferentObject() {
        Letter anotherLetter = new Letter("Анна", "Мария", "Встреча в 15:00");
        Map<String, Object> map = ObjectMapper.objectToMap(anotherLetter);

        assertEquals("Анна", map.get("from"));
        assertEquals("Мария", map.get("to"));
        assertEquals("Встреча в 15:00", map.get("content"));

        Letter restored = ObjectMapper.mapToObject(map, Letter.class);
        assertEquals(anotherLetter.getFrom(), restored.getFrom());
        assertEquals(anotherLetter.getTo(), restored.getTo());
        assertEquals(anotherLetter.getContent(), restored.getContent());
    }
}