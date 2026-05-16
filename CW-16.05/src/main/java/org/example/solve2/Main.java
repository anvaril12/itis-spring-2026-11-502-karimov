package org.example.solve2;

public class Main {
    public static void main(String[] args) {
        long n = 100_000_000;
        int threads = 8;

        long start = System.nanoTime();
        PiCalulator.calculateOne(n);
        long time1 = System.nanoTime() - start;

        System.out.println("однопоточный:");
        System.out.println("время = " + time1 / 1_000_000 + " мс");
        System.out.println();

        try {
            start = System.nanoTime();
            PiCalulator.calculateMore(n, threads);
            long time2 = System.nanoTime() - start;
            System.out.println("1)многопоточный (суммы): " + time2 / 1_000_000 + " мс");
            System.out.println("ускорение: " + String.format("%.2f", (double) time1 / time2) + "x");
        } catch (Exception e) {
            System.out.println("ошибка: " + e.getMessage());
        }
        System.out.println();

        try {
            start = System.nanoTime();
            PiCalulator.calculateMultiThreadWithLock(n, threads);
            long time3 = System.nanoTime() - start;

            System.out.println("2)многопоточный (Lock):");
            System.out.println("время = " + time3 / 1_000_000 + " мс");
            System.out.println("ускорение = " + String.format("%.2f", (double) time1 / time3) + "x");
        } catch (Exception e) {
            System.out.println("ошибка: " + e.getMessage());
        }
    }
}
