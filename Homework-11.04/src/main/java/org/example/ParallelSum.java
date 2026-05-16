package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelSum implements SumCalculator {
    private final int threadCount;

    public ParallelSum(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public long sum(int[] array) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<Long>> futures = new ArrayList<>();

        int chunkSize = array.length / threadCount;

        for (int i = 0; i < threadCount; i++) {
            final int start = i * chunkSize;
            final int end = (i == threadCount - 1) ? array.length : start + chunkSize;

            Callable<Long> task = () -> {
                long partialSum = 0;
                for (int j = start; j < end; j++) {
                    partialSum += array[j];
                }
                return partialSum;
            };

            futures.add(executor.submit(task));
        }

        long total = 0;
        for (Future<Long> future : futures) {
            try {
                total += future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return total;
    }
}