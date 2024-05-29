package com.pluralsight;

public class Topping {
    private String name;
    private boolean premium;

    public Topping(String name, boolean premium) {
        this.name = name;
        this.premium = premium;
    }

    public double getPrice() {
        // Premium toppings cost $1, while regular toppings are free
        return premium ? 1.0 : 0.0;
    }

    @Override
    public String toString() {
        return name;
    }
}
