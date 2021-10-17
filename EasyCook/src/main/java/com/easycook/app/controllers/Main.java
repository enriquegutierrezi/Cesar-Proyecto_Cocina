package com.easycook.app.controllers;

import java.util.List;

import com.easycook.app.entities.Consumer;

public class Main {
    public static void main(String[] args) {
        ConsumerController consumerController = new ConsumerController();
        Consumer consumer = new Consumer("ConsumerTest");
        
        consumerController.createConsumer(consumer);
        Consumer newConsumer = consumerController.findByName("ConsumerTest");
        System.out.println(newConsumer);

        List<Consumer> consumers = consumerController.findAllConsumers();
        consumers.forEach(System.out::println);

        consumerController.updateConsumer(consumer);

        consumers = consumerController.findAllConsumers();
        consumers.forEach(c -> System.out.println(c));

        newConsumer = consumerController.findByName("ConsumerTest");
        System.out.println(newConsumer.get_id().toString());

        consumerController.deleteConsumer(newConsumer);

        consumers = consumerController.findAllConsumers();
        consumers.forEach(System.out::println);
    }
}
