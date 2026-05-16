package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class Storage {
    private final Queue<Product> products = new LinkedList<>();

    public synchronized void add(Product product) {
        products.add(product);
        notifyAll();
    }

    public synchronized Product remove() {
        while (products.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return products.poll();
    }

    public synchronized int size() {
        return products.size();
    }
}