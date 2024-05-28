package com.pluralsight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sandwich {
    private static final Map<String, Double> TOPPING_PRICES = createToppingPrices();
    private Double totalPrice;

    private static Map<String, Double> createToppingPrices() {
        Map<String, Double> prices = new HashMap<>();

        // Regular Toppings
        prices.put("lettuce", 0.0);
        prices.put("peppers", 0.0);
        prices.put("onions", 0.0);
        prices.put("tomatoes", 0.0);
        prices.put("jalapenos", 0.0);
        prices.put("cucumbers", 0.0);
        prices.put("pickles", 0.0);
        prices.put("guacamole", 0.0);
        prices.put("mushrooms", 0.0);

        // Premium Toppings
        prices.put("steak", 1.0);
        prices.put("ham", 1.0);
        prices.put("salami", 1.0);
        prices.put("roast beef", 1.0);
        prices.put("chicken", 1.0);
        prices.put("bacon", 1.0);

// Regular Cheese
        prices.put("american", 0.5);
        prices.put("provolone", 0.5);
        prices.put("cheddar", 0.5);
        prices.put("swiss", 0.5);

        // Premium Cheese
        prices.put("extra cheese", 0.3); // Additional cost for premium cheese
       return prices;
    }

    private static String size;
    private static String breadType;
    static List<String> toppings;
    private static List<String> extras;
    private static List<String> sauces;
    private static boolean toasted;

    public Sandwich(String size, String breadType, List<String> toppings, List<String> extras, List<String> sauces, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toppings = toppings;
        this.extras = extras;
        this.sauces = sauces;
        this.toasted = toasted;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;

        // Calculate the cost of regular toppings
        for (String topping : toppings) {
            totalPrice += TOPPING_PRICES.getOrDefault(topping, 0.0);
        }

        // Calculate the cost of premium toppings
        for (String extra : extras) {
            totalPrice += 0.5; // Additional cost for premium toppings
        }

        // Add the cost of sauces (assuming sauces are free)
        totalPrice += sauces.size();

        return totalPrice;

    }



    public static String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public static String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public static List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public static List<String> getExtras() {
        return extras;
    }

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }

    public static List<String> getSauces() {
        return sauces;
    }

    public void setSauces(List<String> sauces) {
        this.sauces = sauces;
    }

    public static boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size: ").append(size).append("\n");
        sb.append("Bread Type: ").append(breadType).append("\n");
        sb.append("Toppings: ").append(toppings).append("\n");
        sb.append("Extras: ").append(extras).append("\n");
        sb.append("Sauces: ").append(sauces).append("\n");
        sb.append("Toasted: ").append(toasted ? "Yes" : "No");
        return sb.toString();
    }
}