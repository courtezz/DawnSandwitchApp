package com.pluralsight;

import com.pluralsight.Sandwich;

import java.util.ArrayList;
import java.util.List;

class Order {
    private List<Sandwich> sandwiches;
    private List<String> drinks;
    private List<String> chips;
    private double totalPrice;

    public Order() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();
        totalPrice = 0.0;
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
        totalPrice += calculateSandwichPrice(sandwich);
    }

    public void addDrink(String drink) {
        drinks.add(drink);
        totalPrice += 2.0; // Assuming fixed price for all drinks
    }

    public void addChips(String chipsType) {
        chips.add(chipsType);
        totalPrice += 1.5; // Assuming fixed price for all chips
    }

    private double calculateSandwichPrice(Sandwich sandwich) {
        double basePrice = 0.0;
        switch (sandwich.getSize()) {
            case "4":
                basePrice += 4.99;
                break;
            case "8":
                basePrice += 7.99;
                break;
            case "12":
                basePrice += 10.99;
                break;
            default:
                break;
        }

        // Additional charges for premium toppings can be added here

        return basePrice;
    }

    public double calculateTotalPrice() {
        return totalPrice;
    }

    public void clear() {
        sandwiches.clear();
        drinks.clear();
        chips.clear();
        totalPrice = 0.0;
    }

    public void displayOrder() {
        System.out.println("\nOrder Details:");

        // Display sandwiches
        if (!sandwiches.isEmpty()) {
            System.out.println("Sandwiches:");
            for (Sandwich sandwich : sandwiches) {
                System.out.println(sandwich);
            }
        }

        // Display drinks
        if (!drinks.isEmpty()) {
            System.out.println("Drinks:");
            for (String drink : drinks) {
                System.out.println("- " + drink);
            }
        }

        // Display chips
        if (!chips.isEmpty()) {
            System.out.println("Chips:");
            for (String chip : chips) {
                System.out.println("- " + chip);
            }
        }

        System.out.println("Total Price: $" + totalPrice);
    }

    public Sandwich[] getSandwiches() {
        return sandwiches.toArray(new Sandwich[0]);
    }

    public String[] getDrinks() {
        return drinks.toArray(new String[0]);
    }

    public String[] getChips() {
        return chips.toArray(new String[0]);
    }
}
