package org.example;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapper {
    public static Map<String, Object> objectToMap(Object object) {
        if (object == null) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();
        Class<?> currentClass = object.getClass();

        while (currentClass != null && currentClass != Object.class) {
            Field[] fields = currentClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                try {
                    Object value = field.get(object);

                    if (value != null && !isSimpleType(value.getClass())) {
                        value = objectToMap(value);
                    }

                    result.put(field.getName(), value);

                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Ошибка доступа к полю: " + field.getName(), e);
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return result;
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> targetClass) {
        if (map == null || targetClass == null) {
            return null;
        }

        try {
            T object = targetClass.getDeclaredConstructor().newInstance();
            Class<?> currentClass = targetClass;

            while (currentClass != null && currentClass != Object.class) {
                Field[] fields = currentClass.getDeclaredFields();

                for (Field field : fields) {
                    if (map.containsKey(field.getName())) {
                        Object value = map.get(field.getName());
                        field.setAccessible(true);

                        if (value instanceof Map && !isSimpleType(field.getType())) {
                            Object nestedObject = mapToObject((Map<String, Object>) value, field.getType());
                            field.set(object, nestedObject);
                        } else {
                            field.set(object, convertValue(value, field.getType()));
                        }
                    }
                }

                currentClass = currentClass.getSuperclass();
            }

            return object;

        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать объект типа: " + targetClass.getName(), e);
        }
    }

    private static boolean isSimpleType(Class<?> type) {
        return type.isPrimitive() ||
                type == String.class ||
                type == Integer.class ||
                type == Double.class ||
                type == Boolean.class ||
                type == Long.class ||
                type == Float.class ||
                type == Byte.class ||
                type == Character.class ||
                type == Short.class ||
                type.isEnum();
    }

    private static Object convertValue(Object value, Class<?> targetType) {
        if (value == null) {
            return null;
        }

        if (targetType.isInstance(value)) {
            return value;
        }

        if (targetType == int.class || targetType == Integer.class) {
            return ((Number) value).intValue();
        } else if (targetType == long.class || targetType == Long.class) {
            return ((Number) value).longValue();
        } else if (targetType == double.class || targetType == Double.class) {
            return ((Number) value).doubleValue();
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return value;
        } else if (targetType == String.class) {
            return value.toString();
        }

        return value;
    }
}
