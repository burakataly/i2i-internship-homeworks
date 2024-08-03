package com.example.spring.controller;

import com.example.spring.model.Subscriber;
import com.example.spring.service.SubscriberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {
    private final SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @GetMapping
    public List<Subscriber> getAllSubscribers(){
        return subscriberService.getAllSubscribers();
    }

    @GetMapping("/{subscriberId}")
    public Subscriber getSubscriberById(@PathVariable Long subscriberId){
        return subscriberService.getSubscriberById(subscriberId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Subscriber createSubscriber(@RequestBody Subscriber subscriber){
        return subscriberService.createSubscriber(subscriber);
    }

    @DeleteMapping("/{subscriberId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubscriber(@PathVariable Long subscriberId){
        subscriberService.deleteSubscriber(subscriberId);
    }
}
