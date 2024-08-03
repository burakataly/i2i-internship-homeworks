package com.example.spring.repository;

import com.example.spring.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubscriberRepository extends JpaRepository<Subscriber, Long> {
}
