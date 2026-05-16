package org.example;

import java.util.Random;

public class Producer extends Thread {
    private final Storage storage;
    private Consumer consumer;
    private final Random random = new Random();
    private int productId = 1;
    private boolean sleeping = false;

    public Producer(Storage storage, Consumer consumer) {
        this.storage = storage;
        this.consumer = consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100 + random.nextInt(500));

                if (storage.size() == 100) {
                    System.out.println("на складе 100 товаров, producer засыпает");
                    synchronized (this) {
                        sleeping = true;
                        wait();
                        sleeping = false;
                        System.out.println("producer проснулся, продолжает производить");
                    }
                }

                Product product = new Product(productId);
                storage.add(product);
                System.out.println("producer добавил " + product + " | на складе: " + storage.size());
                productId++;

                if (storage.size() == 90) {
                    System.out.println("на складе 90 товаров, consumer просыпается");
                    synchronized (consumer) {
                        consumer.notify();
                    }
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void wakeUp() {
        synchronized (this) {
            if (sleeping) {
                System.out.println("на складе 10 товаров, consumer будит producer");
                notify();
            }
        }
    }
}