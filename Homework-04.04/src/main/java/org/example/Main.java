package org.example;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();

        Consumer consumer = new Consumer(storage, null);
        Producer producer = new Producer(storage, consumer);
        consumer.setProducer(producer);

        System.out.println("==========================================");

        producer.start();
        consumer.start();
    }
}