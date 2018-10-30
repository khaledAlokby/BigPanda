package com.bigpanda.task;


import com.bigpanda.task.observe.Consumer;
import com.bigpanda.task.observe.Producer;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import javax.annotation.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@PropertySource("classpath:application.properties")
@SpringBootApplication
@Controller
public class SpringApp {


    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext app = SpringApplication.run(SpringApp.class, args);
        //ShellComand.executeCommand(app.getResource("classpath:generator-linux-amd64").getURL().getPath());
        NonBlockingQueue<JSONObject> sharedQueue = new NonBlockingQueue<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Producer producer = new Producer(sharedQueue,app.getResource("classpath:generator-linux-amd64").getURL().getPath());
        Consumer consumer = new Consumer(sharedQueue);
        executor.submit(producer);
        executor.submit(consumer);


    }
}

