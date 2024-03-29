package com.pe.car.entity;

public class Car {
    private final int id;
    private final String model;
    private final int price;

    public Car(int id, String model, int price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }
}