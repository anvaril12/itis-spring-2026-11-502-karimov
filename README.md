public static Map<String, Object> objectToMap(Object object)
Преобразует любой объект в Map: ключ — имя поля, значение — значение поля. Поддерживает вложенные объекты (рекурсия) и наследование.

public static <T> T mapToObject(Map<String, Object> map, Class<T> targetClass)
Восстанавливает объект из Map: создаёт экземпляр через пустой конструктор и заполняет поля значениями из Map. Поддерживает вложенные объекты и преобразование типов.

private static boolean isSimpleType(Class<?> type)
Проверяет, является ли тип "простым" (примитив, строка, число, enum) — такие типы не нужно рекурсивно разбирать на поля.

private static Object convertValue(Object value, Class<?> targetType)
Преобразует значение в целевой тип (например, Integer → int, Double → double, любой тип → String).
