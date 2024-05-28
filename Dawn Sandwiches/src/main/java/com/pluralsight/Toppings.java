package com.pluralsight;

public class Toppings {
    private final String name;
    private final double price;

    public Toppings(String name, double price) {
        this.name = name;
        this.price = price;

    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}


