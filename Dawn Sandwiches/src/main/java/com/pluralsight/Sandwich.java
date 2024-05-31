package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String size;
    private String bread;
    private List<String> regularToppings;
    private List<String> premiumToppings;
    private boolean extraMeat;
    private List<String> sauces;
    private boolean toasted;

    private static final double BASE_PRICE_4_INCH = 4.99;
    private static final double BASE_PRICE_8_INCH = 7.99;
    private static final double BASE_PRICE_12_INCH = 10.99;
    private static final double MEATS_PRICE = 1.00;
    private static final double EXTRA_TOPPING_PRICE = 0.50;
    private static final double CHEESE_PRICE = 0.75;

    public Sandwich(String size, String bread) {
        this.size = size;
        this.bread = bread;
        this.regularToppings = new ArrayList<>();
        this.premiumToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.extraMeat = false;
        this.toasted = false;
    }

    public double calculateCost() {
        double basePrice = 0;
        switch (size) {
            case "4":
                basePrice = BASE_PRICE_4_INCH;
                break;
            case "8":
                basePrice = BASE_PRICE_8_INCH;
                break;
            case "12":
                basePrice = BASE_PRICE_12_INCH;
                break;
            default:
                throw new IllegalArgumentException("Invalid sandwich size: " + size);
        }

        basePrice += MEATS_PRICE;
        basePrice += (regularToppings.size() + premiumToppings.size()) * EXTRA_TOPPING_PRICE;
        basePrice += premiumToppings.size() * EXTRA_TOPPING_PRICE;
        basePrice += premiumToppings.size() * CHEESE_PRICE;
        return basePrice;
    }

    @Override
    public String toString() {
        return "Size: " + size + "\", Regular Toppings: " + regularToppings +
                ", Premium Toppings: " + premiumToppings + ", Toasted: " + toasted +
                ", Cost: $" + calculateCost();
    }

    // Other methods (getters, setters, etc.)
    public String getSize() {
        return size;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public List<String> getRegularToppings() {
        return regularToppings;
    }

    public List<String> getPremiumToppings() {
        return premiumToppings;
    }

    public void addRegularTopping(String topping) {
        regularToppings.add(topping);
    }

    public void addPremiumTopping(String topping) {
        premiumToppings.add(topping);
    }

    public List<String> getSauces() {
        return sauces;
    }

    public void addSauce(String sauce) {
        sauces.add(sauce);
    }

    public boolean hasExtraMeat() {
        return extraMeat;
    }

    public void setExtraMeat(boolean extraMeat) {
        this.extraMeat = extraMeat;
    }
    public void setExtraCheese(boolean extraCheese) {
        if (extraCheese) {
            // Add the price of extra cheese if requested
            double calculateCost = CHEESE_PRICE;
        }
    }

    public String getBread() {
        return bread;
    }

    public void removeTopping(String topping) {
        // Remove the topping from the appropriate list
        if (regularToppings.contains(topping)) {
            regularToppings.remove(topping);
        } else if (premiumToppings.contains(topping)) {
            premiumToppings.remove(topping);
        }
    }


    public void setCheese(String cheeseInput) {
    }
}

