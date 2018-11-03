package com.bigpanda.task.observe;

import com.bigpanda.task.NonBlockingQueue;
import org.json.JSONObject;
import rx.Observer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable{
    public volatile static Map<String, Long> eventsMap = new HashMap<>();
    public volatile static Map<String, Long> dataMap = new HashMap<>();
    private final NonBlockingQueue<JSONObject> sharedQueue;

    public Consumer(NonBlockingQueue<JSONObject> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                JSONObject msg = sharedQueue.dequeue();
                System.out.println(Thread.currentThread() +" in Consumer "+msg);
                String eventType = msg.getString("event_type");
                if (eventsMap.containsKey(eventType)){
                    eventsMap.put(eventType,eventsMap.get(eventType)+1);
                }else {
                    eventsMap.put(eventType, (long) 1);
                }
                String data = msg.getString("data");
                if (dataMap.containsKey(data)){
                    dataMap.put(data,dataMap.get(data)+1);
                }else {
                    dataMap.put(data, (long) 1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
/*    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable throwable) {



    }

    @Override
    public void onNext(Object o) {
        System.out.println("******************************");
        System.out.println(Thread.currentThread().getName());
        JSONObject message = new JSONObject(o.toString());
        System.out.println("---------------------> "+message.toString());
    }*/
}
