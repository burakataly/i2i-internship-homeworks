package com.example.spring.service;

import com.example.spring.model.Subscriber;
import com.example.spring.repository.ISubscriberRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SubscriberService {
    private final ISubscriberRepository subscriberRepository;
    private static final AtomicLong idGenerator = new AtomicLong(0);

    public SubscriberService(ISubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public List<Subscriber> getAllSubscribers() {
        return subscriberRepository.findAll();
    }

    public Subscriber getSubscriberById(Long subscriberId){
        return subscriberRepository.findById(subscriberId).orElse(null);
    }

    public Subscriber createSubscriber(Subscriber subscriber) {
        Subscriber newSubscriber = Subscriber.builder()
                .id(generateUniqueId())
                .name(subscriber.getName())
                .surname(subscriber.getSurname())
                .MSISDN(subscriber.getMSISDN())
                .tariff_id(subscriber.getTariff_id())
                .start_date(new Date())
                .build();
        return subscriberRepository.save(newSubscriber);
    }

    public void deleteSubscriber(Long subscriberId) {
        subscriberRepository.deleteById(subscriberId);
    }

    private Long generateUniqueId() {
        return idGenerator.incrementAndGet();
    }
}
