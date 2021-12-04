package com.practical.concurrency.simple;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import static java.lang.String.format;

public class SimpleBehavior extends AbstractBehavior<String> {

    private SimpleBehavior(ActorContext<String> context) {
        super(context);
    }

    public static Behavior<String> create() {
        return Behaviors.setup(SimpleBehavior::new);
    }

    @Override
    public Receive<String> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals("Hi", () -> {
                    System.out.println("How are you?");
                    return this;
                })
                .onMessageEquals("Who are you ?", () -> {
                    System.out.println(format("Path of Actor : %s", getContext().getSelf().path()));
                    return this;
                })
                .onMessageEquals("create", () -> {
                    ActorRef<String> secondActor = getContext().spawn(SimpleBehavior.create(), "SecondActor");
                    secondActor.tell("Who are you ?");
                    return this;
                })
                .onAnyMessage(message -> {
                    System.out.println(format("Message Received : %s", message));
                    return this;
                })
                .build();
    }
}
