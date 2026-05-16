package org.example.solve1;

public class Main {
    public static void main(String[] args) {
        Validator validator = new Validator();


        Address address = new Address("Дубравная", "Казань", "420000");

        User user = new User("Анвар", 18, "kar_anvar@mail.ru", address);

        try {
            validator.validate(user);
            System.out.println("все норм");
        } catch (ValidateException e) {
            System.out.println("не норм");
            e.printError();
        }
    }
}
