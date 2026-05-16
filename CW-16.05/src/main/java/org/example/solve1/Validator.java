package org.example.solve1;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Validator {

    private List<String> errors = new ArrayList<>();

    public void validate(Object obj) throws ValidateException {
        if(obj == null) {
            throw new ValidateException(List.of("Обьект пустой"));
        }
        errors.clear();
        validateRecursive(obj, "");
        if (!errors.isEmpty()) {
            throw new ValidateException(errors);
        }
    }


    private void validateRecursive(Object obj, String path) {
        if (obj == null) return;

        Class<?> clazz = obj.getClass();

        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) {
                    continue;
                }

                field.setAccessible(true);
                String fieldPath = path.isEmpty() ? field.getName() : path + "." + field.getName();

                try {
                    Object value = field.get(obj);
                    validateField(value, field, fieldPath);

                    if (field.isAnnotationPresent(Valid.class) && value != null) {
                        validateRecursive(value, fieldPath);
                    }
                } catch (IllegalAccessException e) {
                    errors.add(fieldPath + "ошибка " + e.getMessage());
                } catch (ValidateException e) {
                    throw new RuntimeException(e);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    private void validateField(Object obj, Field field, String fieldPath) throws ValidateException {

        if (field.isAnnotationPresent(NotNull.class) && obj == null) {
            errors.add(fieldPath + "должен быть не ноль");
            return;
        }

        if (obj == null) return;


        if (field.isAnnotationPresent(Min.class)) {
            Min minAnno = field.getAnnotation(Min.class);
            if (obj instanceof Number) {
                double num = ((Number) obj).doubleValue();
                if (num < minAnno.value()) {
                    errors.add(fieldPath + ", должно быть больше чем " + minAnno.value() + " (а текущее: " + num + ")");
                }
            }
        }

        if(field.isAnnotationPresent(Max.class)) {
            Max maxAnno = field.getAnnotation(Max.class);
            if(obj instanceof Number) {
                double num = ((Number) obj).doubleValue();
                if(num > maxAnno.value()) {
                    errors.add(fieldPath + "должно быть меньше чем " + maxAnno.value() + "(а текущее: " + num + ")");
                }
            }
        }

        if (field.isAnnotationPresent(Size.class)) {
            Size sizeAnno = field.getAnnotation(Size.class);
            int len = -1;

            if (obj instanceof String) {
                len = ((String) obj).length();
            } else if (obj instanceof Collection) {
                len = ((Collection<?>) obj).size();
            }

            if (len >= 0) {
                if (len < sizeAnno.min()) {
                    errors.add(fieldPath + " размер должен быть больше чем  " + sizeAnno.min() + " (а текущий размер -  " + len + ")");
                } else if (len > sizeAnno.max()) {
                    errors.add(fieldPath + " размер должен быть меньше чем  " + sizeAnno.max() + " (а текущий размер  " + len + ")");
                }
            }
        }

        if (field.isAnnotationPresent(Email.class) && obj instanceof String) {
            String email = (String) obj;
            if (!isValidEmail(email)) {
                errors.add(fieldPath + " некорректная записали имэил");
            }
        }
    }

    private boolean isValidEmail(String email) {
        if (email == null) return false;
        int atIndex = email.indexOf('@');
        if (atIndex < 0 || atIndex == 0 || atIndex == email.length() - 1) {
            return false;
        }

        String afterAt = email.substring(atIndex + 1);
        int dotIndex = afterAt.indexOf('.');
        if (dotIndex < 0 || dotIndex == 0 || dotIndex == afterAt.length() - 1) return false;

        return !email.contains(" ");
    }
}
