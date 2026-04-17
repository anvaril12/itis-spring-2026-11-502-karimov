package org.example;

public class SingleThreadSum implements SumCalculator {

    @Override
    public long sum(int[] array) {
        long total = 0;
        for(int num : array) {
            total+=num;
        }
        return total;
    }
}
