package com.easycook.app.entities;

public class Consumer extends Guest {
    int number;

    public Consumer(int id, String name, int number) {
        super(id, name);
        this.number = number;
    }
}
