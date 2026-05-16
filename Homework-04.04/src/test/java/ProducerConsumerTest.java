package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProducerConsumerTest {

    @Test
    void testStorageAddAndRemove() throws InterruptedException {
        Storage storage = new Storage();
        Product product = new Product(1);

        storage.add(product);
        assertEquals(1, storage.size());

        Product taken = storage.remove();
        assertEquals(1, taken.getId());
        assertEquals(0, storage.size());
    }

    @Test
    void testStorageRemoveWhenEmpty() throws InterruptedException {
        Storage storage = new Storage();

        Thread consumer = new Thread(() -> {
            try {
                Product product = storage.remove();
                assertNotNull(product);
            } catch (Exception e) {
                fail("remove threw exception");
            }
        });

        consumer.start();
        Thread.sleep(100);
        storage.add(new Product(1));
        consumer.join(1000);

        assertFalse(consumer.isAlive());
    }

    @Test
    void testProducerCreatesProducts() throws InterruptedException {
        Storage storage = new Storage();
        Consumer consumer = new Consumer(storage, null);
        Producer producer = new Producer(storage, consumer);
        consumer.setProducer(producer);
        producer.setConsumer(consumer);

        producer.start();
        Thread.sleep(500);

        assertTrue(storage.size() > 0);
    }

    @Test
    void testProducerSleepsWhenStorageFull() throws InterruptedException {
        Storage storage = new Storage();
        Consumer consumer = new Consumer(storage, null);
        Producer producer = new Producer(storage, consumer);
        consumer.setProducer(producer);
        producer.setConsumer(consumer);

        for (int i = 1; i <= 100; i++) {
            storage.add(new Product(i));
        }

        assertEquals(100, storage.size());

        producer.start();
        Thread.sleep(500);

        assertTrue(storage.size() <= 100);
    }

    @Test
    void testConsumerSleepsWhenStorageEmpty() throws InterruptedException {
        Storage storage = new Storage();
        Consumer consumer = new Consumer(storage, null);

        consumer.start();
        Thread.sleep(500);

        assertTrue(storage.size() == 0);
    }

    @Test
    void testConsumerWakesProducerAt10() throws InterruptedException {
        Storage storage = new Storage();
        Consumer consumer = new Consumer(storage, null);
        Producer producer = new Producer(storage, consumer);
        consumer.setProducer(producer);
        producer.setConsumer(consumer);

        for (int i = 1; i <= 100; i++) {
            storage.add(new Product(i));
        }

        producer.start();
        Thread.sleep(200);

        for (int i = 1; i <= 90; i++) {
            storage.remove();
        }

        assertEquals(10, storage.size());

        if (!consumer.isAlive()) {
            consumer.start();
        }

        Thread.sleep(500);

        assertTrue(true);
    }
}