package org.example.solve2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class PiCalulator {

    public static double calculateOne(long n) {
        double sum = 0.0;
        for (long i = 0; i < n; i++) {
            sum += Math.pow(-1, i) / (2 * i + 1);
        }
        return 4 * sum;
    }

    public static double calculateMore(long n, int numThreads) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Double>> futures = new ArrayList<>();

        long chunkSize = n / numThreads;

        for (int t = 0; t < numThreads; t++) {
            final long start = t * chunkSize;
            final long end = (t == numThreads - 1) ? n : start + chunkSize;

            Callable<Double> task = () -> {
                double partialSum = 0.0;
                for (long i = start; i < end; i++) {
                    partialSum += Math.pow(-1, i) / (2 * i + 1);
                }
                return partialSum;
            };

            futures.add(executor.submit(task));
        }

        double totalSum = 0.0;
        for (Future<Double> future : futures) {
            totalSum += future.get();
        }

        executor.shutdown();
        return 4 * totalSum;
    }

    static class SumHolder {
        private double sum = 0.0;

        public void add(double value) {
            this.sum += value;
        }

        public double get() {
            return sum;
        }
    }

    public static double calculateMultiThreadWithLock(long n, int numThreads) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        ReentrantLock lock = new ReentrantLock();
        SumHolder sharedSum = new SumHolder();

        long chunkSize = n / numThreads;

        for (int t = 0; t < numThreads; t++) {
            final long start = t * chunkSize;
            final long end = (t == numThreads - 1) ? n : start + chunkSize;

            Runnable task = () -> {
                double partialSum = 0.0;
                for (long i = start; i < end; i++) {
                    partialSum += Math.pow(-1, i) / (2 * i + 1);
                }
                lock.lock();
                try {
                    sharedSum.add(partialSum);
                } finally {
                    lock.unlock();
                }
            };

            executor.submit(task);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        return 4 * sharedSum.get();
    }
}
