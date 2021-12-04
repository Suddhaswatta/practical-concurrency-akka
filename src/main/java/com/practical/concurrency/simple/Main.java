package com.practical.concurrency.simple;

import akka.actor.typed.ActorSystem;

public class Main {
    public static void main(String[] args) {
        ActorSystem<String> firstActorSystem = ActorSystem.create(SimpleBehavior.create(), "FirstActorSystem");
        firstActorSystem.tell("Hi");
        firstActorSystem.tell("create");
        firstActorSystem.tell("Who are you ?");
    }
}
