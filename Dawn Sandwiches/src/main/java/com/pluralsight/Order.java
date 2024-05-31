package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

class Order {
    private final List<Sandwich> sandwiches;
    private final List<String> drinks;
    private final List<String> chips;
    private double totalPrice;

    public Order() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();
        totalPrice = 0.0;
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
        totalPrice += sandwich.calculateCost(); // Using Sandwich's method to calculate cost
    }

    public void addDrink(String drink) {
        drinks.add(drink);
        totalPrice += 2.0; // Assuming fixed price for all drinks
    }

    public void addChips(String chipsType) {
        chips.add(chipsType);
        totalPrice += 1.5; // Assuming fixed price for all chips
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

    public String displayOrder() {
        StringBuilder output = new StringBuilder();
        output.append("\nOrder Details: \n");

        // Display sandwiches
        if (!sandwiches.isEmpty()) {
            output.append("\nSandwiches: \n");
            for (Sandwich sandwich : sandwiches) {
                output.append("- ").append(sandwich.toString()).append("\n");
            }
        }

        // Display drinks
        if (!drinks.isEmpty()) {
            output.append("\nDrinks: \n");
            for (String drink : drinks) {
                output.append("- ").append(drink).append("\n");
            }
        }

        // Display chips
        if (!chips.isEmpty()) {
            output.append("\nChips: \n");
            for (String chip : chips) {
                output.append("- ").append(chip).append("\n");
            }
        }

        output.append("\nTotal Price: \n").append(totalPrice);
        return output.toString();
    }
}


