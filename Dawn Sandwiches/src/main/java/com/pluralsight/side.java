package com.pluralsight;

public class side {
    private String size;
    private String name;
    private double price;

    public void Side(String size, String name, double price) {
        this.size = size;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return size + " " + name + ": $" + price;
    }
}
