package com.pluralsight;

import java.util.ArrayList;

public class Sandwich {
    private String size;
    private ArrayList<String> regularToppings;
    private ArrayList<String> premiumToppings;
    private boolean toasted;

    private static final double PRICE_4_INCH = 5.50;
    private static final double PRICE_8_INCH = 7.00;
    private static final double PRICE_12_INCH = 8.50;
    private static final double MEATS_PRICE = 1.00;
    private static final double EXTRA_MEAT_PRICE = 0.50;
    private static final double CHEESE_PRICE = 0.75;
    private static final double EXTRA_CHEESE_PRICE = 0.30;

    public Sandwich(String size, String bread) {
        this.size = size;
        regularToppings = new ArrayList<>();
        premiumToppings = new ArrayList<>();
        toasted = false;
    }

    public double calculateCost() {
        double basePrice = 0;
        switch (size) {
            case "4":
                basePrice = PRICE_4_INCH;
                break;
            case "8":
                basePrice = PRICE_8_INCH;
                break;
            case "12":
                basePrice = PRICE_12_INCH;
                break;
            default:
                throw new IllegalArgumentException("Invalid sandwich size: " + size);
        }

        basePrice += MEATS_PRICE + CHEESE_PRICE;
        basePrice += premiumToppings.size() * (EXTRA_MEAT_PRICE + EXTRA_CHEESE_PRICE);
        return basePrice;
    }

    public String toString() {
        return "Size: " + size + "\", Regular Toppings: " + regularToppings +
                ", Premium Toppings: " + premiumToppings + ", Toasted: " + toasted +
                ", Cost: $" + calculateCost();
    }

    public String getSize() {
        return size;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public ArrayList<String> getRegularToppings() {
        return regularToppings;
    }

    public ArrayList<String> getPremiumToppings() {
        return premiumToppings;
    }

    public void addRegularTopping(String topping) {
        regularToppings.add(topping);
    }

    public void addPremiumTopping(String topping) {
        premiumToppings.add(topping);
    }

    public String calculatePrice() {
        return null;
    }

    public String getBread() {
        return null;
    }

    public boolean hasExtraMeat() {
        return false;
    }
}
