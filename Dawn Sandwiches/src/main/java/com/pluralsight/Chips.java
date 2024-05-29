package com.pluralsight;

public class Chips {
    private double price;

    public Chips(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return "Chips: $" + price;
    }
}
