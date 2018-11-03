package com.bigpanda.task.http_api;


import com.bigpanda.task.observe.Consumer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:application.properties")
public class HttpController {


    @RequestMapping(value = "/eventCount", method = RequestMethod.GET)
    public ResponseEntity getEventCount(@RequestParam("type") String type) {
        if (Consumer.eventsMap.containsKey(type)){
            return new ResponseEntity(Consumer.eventsMap.get(type),HttpStatus.OK);
        }
        return new ResponseEntity("The Event Type "+type +" doesn't exist(yet).",HttpStatus.OK);
    }

    @RequestMapping(value = "/dataCount", method = RequestMethod.GET)
    public ResponseEntity getDataCount(@RequestParam("data") String data) {
        if (Consumer.dataMap.containsKey(data)){
            return new ResponseEntity(Consumer.dataMap.get(data),HttpStatus.OK);
        }
        return new ResponseEntity("The Data "+data +" doesn't exist(yet).",HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(){
        return "Hi There";
    }
}