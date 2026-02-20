package Cats;

public class Cat {
    private String name;
    private int age;
    private String color;

    public Cat(String name, String color, int age) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getColor() { return color; }

    @Override
    public String toString() {
        return "Имя: " + name + "; Цвет: " + color  + "; Возраст: " + age;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + color.hashCode() + age;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Cat)) {
            return false;
        }
        Cat cat = (Cat) o;
        return age == cat.age && name.equals(cat.name) && color.equals(cat.color);
    }

}
