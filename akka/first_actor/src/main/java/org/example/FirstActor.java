package org.example;

import akka.actor.AbstractActor;

public class FirstActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("start", s -> getContext().actorSelection("akka://SecondActorSystem@localhost:1911/user/second_actor")
                        .tell("Hello from FirstActor", getSelf()))
                .matchEquals("Hello from SecondActor", s -> System.out.println("FirstActor received: " + s))
                .build();
    }
}
