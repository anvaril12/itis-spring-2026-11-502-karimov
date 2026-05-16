package org.example;

import java.util.Random;

public class Consumer extends Thread {
    private final Storage storage;
    private Producer producer;
    private final Random random = new Random();
    private boolean sleeping = true;

    public Consumer(Storage storage, Producer producer) {
        this.storage = storage;
        this.producer = producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (sleeping) {
                    synchronized (this) {
                        wait();
                        sleeping = false;
                        System.out.println("consumer проснулся, начинает кушать");
                    }
                }

                Thread.sleep(100 + random.nextInt(500));

                if (storage.size() == 0) {
                    System.out.println("на складе 0 товаров, consumer засыпает");
                    sleeping = true;
                    continue;
                }

                storage.remove();
                System.out.println("consumer взял товар | осталось на складе: " + storage.size());

                if (storage.size() == 10) {
                    System.out.println("на складе осталось 10 товаров, бужу producer");
                    producer.wakeUp();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}