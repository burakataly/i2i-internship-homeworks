package org.example;

import akka.actor.AbstractActor;

public class SecondActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("Hello from FirstActor", s -> {
                    System.out.println("SecondActor received: " + s);
                    getSender().tell("Hello from SecondActor", getSelf());
                })
                .build();
    }
}
