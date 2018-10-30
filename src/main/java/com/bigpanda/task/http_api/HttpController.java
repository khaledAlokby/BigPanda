package com.bigpanda.task.http_api;


import com.bigpanda.task.observe.Consumer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:application.properties")
public class HttpController {


    @RequestMapping(value = "/eventCount", method = RequestMethod.GET)
    public int getEventCount(@RequestParam("type") String type) {
        return Consumer.eventsMap.get(type);
    }

    @RequestMapping(value = "/dataCount", method = RequestMethod.GET)
    public int getDataCount(@RequestParam("data") String data) {
        return Consumer.eventsMap.get(data);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(){
        return "Hi There";
    }
}