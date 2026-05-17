package org.example;

import org.junit.jupiter.api.Test;
import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

class BmwCarTest {

    @Test
    void testClassExists() {
        assertNotNull(BmwCar.class);
    }

    @Test
    void testClassImplementsMovable() {
        Class<?>[] interfaces = BmwCar.class.getInterfaces();
        boolean implementsMovable = false;
        for (Class<?> iface : interfaces) {
            if (iface.getName().equals("org.example.Movable")) {
                implementsMovable = true;
                break;
            }
        }
        assertTrue(implementsMovable);
    }

    @Test
    void testClassExtendsCar() {
        Class<?> superclass = BmwCar.class.getSuperclass();
        assertEquals("org.example.Car", superclass.getName());
    }

    @Test
    void testClassHasAnnotation() {
        assertTrue(BmwCar.class.isAnnotationPresent(CarInfo.class));
        CarInfo annotation = BmwCar.class.getAnnotation(CarInfo.class);
        assertEquals("Немецкий автомобиль", annotation.value());
    }

    @Test
    void testClassModifier() {
        int modifiers = BmwCar.class.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isAbstract(modifiers));
    }

    @Test
    void testFieldsCount() {
        Field[] fields = BmwCar.class.getDeclaredFields();
        assertEquals(3, fields.length);
    }

    @Test
    void testFieldNames() {
        Field[] fields = BmwCar.class.getDeclaredFields();
        String[] fieldNames = {"color", "yearOfRelease", "bodyType"};
        for (String expectedName : fieldNames) {
            boolean found = false;
            for (Field field : fields) {
                if (field.getName().equals(expectedName)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "Поле " + expectedName + " не найдено");
        }
    }

    @Test
    void testFieldTypes() {
        try {
            Field colorField = BmwCar.class.getDeclaredField("color");
            assertEquals(String.class, colorField.getType());

            Field yearField = BmwCar.class.getDeclaredField("yearOfRelease");
            assertEquals(int.class, yearField.getType());

            Field bodyField = BmwCar.class.getDeclaredField("bodyType");
            assertEquals(String.class, bodyField.getType());
        } catch (NoSuchFieldException e) {
            fail("Поле не найдено");
        }
    }

    @Test
    void testFieldAnnotations() {
        try {
            Field colorField = BmwCar.class.getDeclaredField("color");
            assertTrue(colorField.isAnnotationPresent(FieldDesc.class));

            FieldDesc annotation = colorField.getAnnotation(FieldDesc.class);
            assertEquals("Цвет автомобиля", annotation.value());
        } catch (NoSuchFieldException e) {
            fail("Поле не найдено");
        }
    }

    @Test
    void testMethodsCount() {
        Method[] methods = BmwCar.class.getDeclaredMethods();
        assertTrue(methods.length >= 8);
    }

    @Test
    void testMethodExists() {
        try {
            Method getColor = BmwCar.class.getDeclaredMethod("getColor");
            assertEquals(String.class, getColor.getReturnType());

            Method setColor = BmwCar.class.getDeclaredMethod("setColor", String.class);
            assertEquals(void.class, setColor.getReturnType());

            Method move = BmwCar.class.getDeclaredMethod("move");
            assertEquals(void.class, move.getReturnType());
        } catch (NoSuchMethodException e) {
            fail("Метод не найден");
        }
    }

    @Test
    void testConstructorsCount() {
        Constructor<?>[] constructors = BmwCar.class.getConstructors();
        assertEquals(3, constructors.length);
    }

    @Test
    void testConstructorWithException() {
        try {
            Constructor<?> constructor = BmwCar.class.getConstructor(int.class, String.class);
            Class<?>[] exceptionTypes = constructor.getExceptionTypes();
            assertEquals(1, exceptionTypes.length);
            assertEquals(IllegalArgumentException.class, exceptionTypes[0]);
        } catch (NoSuchMethodException e) {
            fail("Конструктор не найден");
        }
    }

    @Test
    void testConstructorParameters() {
        try {
            Constructor<?> constructor = BmwCar.class.getConstructor(String.class, int.class, String.class);
            Class<?>[] paramTypes = constructor.getParameterTypes();
            assertEquals(3, paramTypes.length);
            assertEquals(String.class, paramTypes[0]);
            assertEquals(int.class, paramTypes[1]);
            assertEquals(String.class, paramTypes[2]);
        } catch (NoSuchMethodException e) {
            fail("Конструктор не найден");
        }
    }
}