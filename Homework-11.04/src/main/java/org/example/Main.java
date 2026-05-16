package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int size = 1_000_000_000;
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }

        SumCalculator singleThread = new SingleThreadSum();
        long start1 = System.currentTimeMillis();
        long result1 = singleThread.sum(array);
        long time1 = System.currentTimeMillis() - start1;

        int cores = Runtime.getRuntime().availableProcessors();

        SumCalculator parallel = new ParallelSum(cores);
        long start2 = System.currentTimeMillis();
        long result2 = parallel.sum(array);
        long time2 = System.currentTimeMillis() - start2;

        System.out.println("Сумма однопоточки: " + result1);
        System.out.println("Сумма многопоточки: " + result2);
        System.out.println("Время однопоточки: " + time1 + " ms");
        System.out.println("Время многопоточки: " + time2 + " ms");
        System.out.println("Совпадают: " + (result1 == result2));
        System.out.println("Ускорение: " + String.format("%.2f", (double) time1 / time2) + "x");
        System.out.println("Количество ядер: " + cores);
    }
}