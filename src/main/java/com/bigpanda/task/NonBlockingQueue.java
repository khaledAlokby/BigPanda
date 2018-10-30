package com.bigpanda.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class NonBlockingQueue<T> {
    List<T> buffer;
    private static final int MAX_QUEUE_SIZE = 100;
    private AtomicBoolean mutex = new AtomicBoolean(false);

    public NonBlockingQueue() {
        buffer = new ArrayList<T>();
    }

    public void enqueue(T value) throws InterruptedException {
        while(true) {
            while(!mutex.compareAndSet(false, true)) {
                Thread.sleep(100);
            }
            if(buffer.size() < MAX_QUEUE_SIZE) {
                buffer.add(value);
                mutex.set(false);
                return;
            } else {
                mutex.set(false);
            }
        }

    }

    public T dequeue() throws InterruptedException {
        T value = null;
        while(true) {
            while(!mutex.compareAndSet(false, true)) {
                Thread.sleep(100);
            }
            if(buffer.size() > 0) {
                value = buffer.remove(0);
                mutex.set(false);
                return value;
            } else {
                mutex.set(false);
            }
        }   
    }
}