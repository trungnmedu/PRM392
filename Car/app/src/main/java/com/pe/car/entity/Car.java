package com.pe.car.entity;

public class Car {
    private final int first;
    private final String second;
    private final int third;

    public Car(int first, String second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public int getThird() {
        return third;
    }
}