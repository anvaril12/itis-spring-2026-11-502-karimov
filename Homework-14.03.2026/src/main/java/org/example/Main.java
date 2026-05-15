package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - сохранить структуру");
        System.out.println("2 - восстановить структуру");
        System.out.print("Выбор: ");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.print("Путь к папке: ");
            new Coder().code(sc.nextLine());
            System.out.println("Структура сохранена в structure.json");
        } else if (choice == 2) {
            System.out.print("Путь к structure.json: ");
            new Decoder().decode(sc.nextLine());
            System.out.println("Структура восстановлена");
        }
    }
}