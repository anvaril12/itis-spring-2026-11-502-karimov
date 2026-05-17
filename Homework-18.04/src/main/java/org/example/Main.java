package org.example;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) {

        inspectClass(BmwCar.class);
    }
    public static void inspectClass(Class<?> class1) {

        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Класс: ");
        System.out.println();
        System.out.println("Имя класса: " + class1.getName());

        int modifiers  = class1.getModifiers();
        System.out.println("Модификатор класса: " + Modifier.toString(modifiers));

        try {
            Annotation[] annotations = class1.getAnnotations();
            if(annotations.length > 0) {
                for(Annotation annotation : annotations) {
                    System.out.println("Аннотации класса: " + annotation.annotationType().getSimpleName());
                }
            } else {
                System.out.println("Класс не имеет аннотаций");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Class<?>[] interfaces = class1.getInterfaces();
            if(interfaces.length > 0) {
                for(Class<?> cl : interfaces) {
                    System.out.println("Класс реализует следующие интерфейсы: " + cl.getName());
                }
            } else {
                System.out.println("Класс не имеет интерфейсов");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Class<?> parentsClasses = class1.getSuperclass();
            if(parentsClasses != null) {
                System.out.println("Родительский класс:" + parentsClasses);
            } else {
                System.out.println("Нет родительского класса");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Поля класса: ");
        System.out.println();
        try {
            Field[] fields = class1.getDeclaredFields();
            if(fields.length > 0) {
                for(Field field : fields) {
                    String name = field.getName();
                    String type = field.getType().getSimpleName();
                    String modificated = Modifier.toString(field.getModifiers());
                    System.out.println("Модификатор: " + modificated + ", Тип: " + type + ", Имя: " + name);

                    Annotation[] annotations = field.getAnnotations();
                    if(annotations.length > 0) {
                        for(Annotation annotation : annotations) {
                            System.out.println("Аннотации поля: " + annotation.annotationType().getSimpleName());
                        }
                    } else {
                        System.out.println("Поле не имеет аннотаций");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("У класса нет полей");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Методы класса: ");
        System.out.println();
        try {
            Method[] methods = class1.getDeclaredMethods();
            if(methods.length > 0) {
                for(Method method : methods) {
                    String modificated = Modifier.toString(method.getModifiers());
                    String returnType = method.getReturnType().getSimpleName();
                    String name = method.getName();

                    System.out.println("Модификатор: " + modificated + ", Тип: " + returnType + ", Имя: " + name);

                    Class<?>[] exceptions = method.getExceptionTypes();
                    if (exceptions.length > 0) {
                        for(Class<?> cl : exceptions) {
                            System.out.println("Метод " + method.getName() + " выбрасывает " + cl.getSimpleName());
                        }
                    } else {
                        System.out.println("Метод не выбрасывает исключения");
                    }

                    Parameter[] parameters = method.getParameters();
                    if(parameters.length > 0) {
                        for(Parameter parameter : parameters) {
                            Annotation[] annotations = parameter.getAnnotations();
                            System.out.println("Имя параметра: " + parameter.getName() + ", Тип параметра: " + parameter.getType().getSimpleName());
                            if(annotations.length > 0) {
                                for(Annotation annotation : annotations) {
                                    System.out.println("Аннотации параметра: " + annotation.annotationType().getSimpleName());
                                }
                            } else {
                                System.out.println("Параметры метода не имеют аннотаций");
                            }
                        }
                    } else {
                        System.out.println("У метода нет параметра");
                    }

                    Annotation[] annotations = method.getAnnotations();
                    if(annotations.length > 0) {
                        for(Annotation annotation : annotations) {
                            System.out.println("Аннотации метода: " + annotation.annotationType().getSimpleName());
                        }
                    } else {
                        System.out.println("Метод не имеет аннотаций");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("У класса нет методов");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Конструкторы класса: ");
        System.out.println();
        try {
            Constructor[] constructors = class1.getConstructors();
            if(constructors.length > 0) {
                for(Constructor constructor : constructors) {
                    String modificated = Modifier.toString(constructor.getModifiers());
                    String name = constructor.getName();
                    System.out.println("Модификатор: " + modificated + ", Имя: " + name);

                    Class<?>[] exceptions = constructor.getExceptionTypes();
                    if(exceptions.length > 0) {
                        for(Class<?> cl : exceptions) {
                            System.out.println("Конструктор " + constructor.getName() + " выбрасывает " + cl.getSimpleName());
                        }
                    } else {
                        System.out.println("Конструтор не выбрасывает исключения");
                    }

                    Parameter[] parameters = constructor.getParameters();
                    if(parameters.length > 0) {
                        for(Parameter parameter : parameters) {
                            Annotation[] annotations = parameter.getAnnotations();
                            System.out.println("Имя параметра: " + parameter.getName() + ", Тип параметра: " + parameter.getType().getSimpleName());
                            if(annotations.length > 0) {
                                for(Annotation annotation : annotations) {
                                    System.out.println("Аннотации параметра: " + annotation.annotationType().getSimpleName());
                                }
                            } else {
                                System.out.println("У параметра нет аннотаций");
                            }
                        }
                    }

                    Annotation[] annotations = constructor.getAnnotations();
                    if(annotations.length > 0) {
                        for(Annotation annotation : annotations) {
                            System.out.println("Аннотации конструктора: " + annotation.annotationType().getSimpleName());
                        }
                    } else {
                        System.out.println("Конструктор не имеет аннотаций");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("У класса нет конструторов");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}