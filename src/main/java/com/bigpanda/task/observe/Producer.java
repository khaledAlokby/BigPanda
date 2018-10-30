package com.bigpanda.task.observe;

import com.bigpanda.task.NonBlockingQueue;

import org.json.JSONObject;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.subjects.PublishSubject;

import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Producer implements Runnable {
    Process p = null;
    private final NonBlockingQueue<JSONObject> sharedQueue;
    private String command;

    public Producer(NonBlockingQueue<JSONObject> sharedQueue, String command) {
        this.sharedQueue = sharedQueue;
        this.command = command;
    }

    @Override
    public void run() {
        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine())!= null) {
                try {
                    JSONObject json = new JSONObject(line);
                    System.out.println(Thread.currentThread() +" in Producer "+json);
                    sharedQueue.enqueue(json);
                }catch (Exception ex){
                    System.out.println("Bad Input : "+line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void onDestroy(){
        if (p.isAlive()){
            p.destroy();
        }
    }

   /* private static PublishSubject<String> publishSubject = PublishSubject.create();
    private static List<String> valuesCache = new ArrayList<>();


    public Producer(Observer consumer) {
        super(subscriber -> {
            Observable.from(valuesCache).doOnNext(consumer::onNext)
                    .doOnCompleted(valuesCache::clear)
                    .subscribe();

            publishSubject.subscribe(consumer::onNext);
            OnSubscribe<JSONObject> subscribe = new OnSubscribe<JSONObject>() {
                @Override
                public void call(Subscriber<? super JSONObject> subscriber) {
                    consumer.onNext(subscriber);
                }
            };
        });
    }


    public void emit(String value) {
        if (publishSubject.hasObservers()) {
            publishSubject.onNext(value);
        } else {
            valuesCache.add(value);
        }
    }*/
}
