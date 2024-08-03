package com.example.spring.controller;

import com.example.spring.model.Subscriber;
import com.example.spring.service.SubscriberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subscribers")
public class SubscriberWebController {
    private final SubscriberService subscriberService;

    public SubscriberWebController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("subscriber", new Subscriber());
        return "form"; // Maps to form.html for creating a new subscriber
    }

    @PostMapping
    public String createSubscriber(@ModelAttribute Subscriber subscriber, Model model) {
        Subscriber savedSubscriber = subscriberService.createSubscriber(subscriber);
        return "redirect:/subscribers/" + savedSubscriber.getId();
    }

    @GetMapping("/{id}")
    public String showSubscriber(@PathVariable Long id, Model model) {
        Subscriber subscriber = subscriberService.getSubscriberById(id);
        if (subscriber != null) {
            model.addAttribute("subscriber", subscriber);
            return "result";
        } else {
            return "redirect:/subscribers/new";
        }
    }

    @GetMapping("/all")
    public String showAllSubscribers(Model model) {
        List<Subscriber> subscribers = subscriberService.getAllSubscribers();
        model.addAttribute("subscribers", subscribers);
        return "subscribers";
    }
}
