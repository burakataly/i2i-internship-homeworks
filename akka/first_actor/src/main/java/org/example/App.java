package org.example;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class App {
    public static void main( String[] args ) {
        Config config = ConfigFactory.load("first_actor.conf");
        ActorSystem actorSystem = ActorSystem.create("FirstActorSystem", config);
        actorSystem.actorOf(Props.create(FirstActor.class), "first_actor");
        actorSystem.actorSelection("/user/first_actor").tell("start", ActorSystem.create().deadLetters());
    }
}
