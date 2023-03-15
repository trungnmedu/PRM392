package com.pe.clock.entity;

public class Clock {
    private final int id;
    private final String name;
    private final int price;

    public Clock(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}