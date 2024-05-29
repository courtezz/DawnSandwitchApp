package com.pluralsight;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SandwichApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Double> prices = new HashMap<>();
    private static final List<String> drinkNames = Arrays.asList("coke", "Pepsi", "dr. pepper", "sprite", "HI-C", "lemonade");
    private static final List<String> chipNames = Arrays.asList("nacho Doritos", "Cheetos", "hot Cheetos", "ranch Doritos");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");

    static {
        // Define the prices of toppings, cheese, and sandwich sizes
        prices.put("lettuce", 0.0);
        prices.put("peppers", 0.0);
        prices.put("onions", 0.0);
        prices.put("tomatoes", 0.0);
        prices.put("jalapenos", 0.0);
        prices.put("cucumbers", 0.0);
        prices.put("pickles", 0.0);
        prices.put("guacamole", 0.0);
        prices.put("mushrooms", 0.0);
        prices.put("steak", 1.0);
        prices.put("ham", 1.0);
        prices.put("salami", 1.0);
        prices.put("roast beef", 1.0);
        prices.put("chicken", 1.0);
        prices.put("bacon", 1.0);
        prices.put("american", 0.5);
        prices.put("provolone", 0.5);
        prices.put("cheddar", 0.5);
        prices.put("swiss", 0.5);
        prices.put("coke", 1.5);
        prices.put("pepsi", 1.5);
        prices.put("dr. pepper", 1.5);
        prices.put("sprite", 1.5);
        prices.put("hi-c", 1.5);
        prices.put("lemonade", 1.5);
        prices.put("nacho doritos", 1.0);
        prices.put("cheetos", 1.0);
        prices.put("hot cheetos", 1.0);
        prices.put("ranch doritos", 1.0);
    }

    public static void main(String[] args) {
        OrderScreen orderScreen = new OrderScreen();
        orderScreen.displayMenu();
        displayMenu();
    }

    private static void displayMenu() {
        while (true) {
            System.out.println("\nWelcome to Dawn Sandwich!");
            System.out.println("1. Place Order");
            System.out.println("2. View Menu");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    placeOrder();
                    break;
                case 2:
                    displayMenuOptions();
                    break;
                case 3:
                    System.out.println("Thank you for visiting Dawn Sandwich. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }

    private static void placeOrder() {
        List<Sandwich> sandwiches = new ArrayList<>();
        List<String> drinks = new ArrayList<>();
        List<String> chips = new ArrayList<>();

        do {
            System.out.println("\nCreating a new sandwich:");
            sandwiches.add(createSandwich());

            System.out.print("\nAdd a drink? (yes/no): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                displayDrinkOptions();
                System.out.print("Enter drink: ");
                String drink = scanner.nextLine().trim();
                if (drinkNames.contains(drink.toLowerCase())) {
                    drinks.add(drink);
                } else {
                    System.out.println("Invalid drink option. Drink not added.");
                }
            }

            System.out.print("\nAdd chips? (yes/no): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                displayChipOptions();
                System.out.print("Enter chips: ");
                String chip = scanner.nextLine().trim();
                if (chipNames.contains(chip.toLowerCase())) {
                    chips.add(chip);
                } else {
                    System.out.println("Invalid chips option. Chips not added.");
                }
            }

            System.out.print("\nWould you like to add another sandwich? (yes/no): ");
        } while (scanner.nextLine().trim().equalsIgnoreCase("yes"));

        displayOrder(sandwiches, drinks, chips);
        saveOrderToFile(sandwiches, drinks, chips);
    }

    private static Sandwich createSandwich() {
        System.out.println("Customize your sandwich:");

        String breadType = selectBreadType();
        String size = selectSize();

        List<String> toppings = selectOptions("Toppings");
        List<String> extras = selectOptions("Extras");
        List<String> sauces = selectOptions("Sauces");

        String toastInput = getUserInput();
        boolean toasted = toastInput.equalsIgnoreCase("yes");

        return new Sandwich(size, breadType, toppings, extras, sauces, toasted);
    }

    private static String selectSize() {
        System.out.println("Select sandwich size:");
        System.out.println("1. 4in");
        System.out.println("2. 8in");
        System.out.println("3. 12in");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                return "4in";
            case 2:
                return "8in";
            case 3:
                return "12in";
            default:
                System.out.println("Invalid choice. Defaulting to Medium size.");
                return "Medium";
        }
    }

    private static String selectBreadType() {
        System.out.println("Select your bread type:");
        System.out.println("1. White");
        System.out.println("2. Wheat");
        System.out.println("3. Rye");
        System.out.println("4. Wrap");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                return "White";
            case 2:
                return "Wheat";
            case 3:
                return "Rye";
            case 4:
                return "Wrap";
            default:
                System.out.println("Invalid choice. Defaulting to White bread.");
                return "White";
        }
    }

    private static List<String> selectOptions(String optionName) {
        List<String> optionsList = new ArrayList<>();
        System.out.println("Select " + optionName + " (type 'done' when finished):");
        while (true) {
            String option = scanner.nextLine().trim();
            if (option.equalsIgnoreCase("done")) {
                break;
            }
            optionsList.add(option);
        }
        return optionsList;
    }

    private static String getUserInput() {
        System.out.print("Would you like your sandwich toasted? (yes/no): ");
        return scanner.nextLine().trim();
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
    private static void displayMenuOptions() {
        System.out.println("\nMenu Options:");

        // Display Regular Toppings
        System.out.println("\nRegular Toppings:");
        prices.forEach((topping, price) -> {
            if (price == 0.0) {
                System.out.println("- " + topping + " ($" + price + ")");
            }
        });

        // Display Premium Toppings
        System.out.println("\nPremium Toppings:");
        prices.forEach((topping, price) -> {
            if (price == 1.0) {
                System.out.println("- " + topping + " ($" + price + ")");
            }
        });

        // Display Cheese Options
        System.out.println("\nCheese options:");
        prices.forEach((cheese, price) -> {
            if (price == 0.5) {
                System.out.println("- " + cheese + " ($" + price + ")");
            }
        });

        // Display Drink Options with Prices
        System.out.println("\nDrink options:");
        for (String drink : drinkNames) {
            System.out.println("- " + drink + " ($" + prices.get(drink.toLowerCase()) + ")");
        }

        // Display Chip Options with Prices
        System.out.println("\nChip options:");
        for (String chip : chipNames) {
            System.out.println("- " + chip + " ($" + prices.get(chip.toLowerCase()) + ")");
        }

        // Display Sandwich Sizes
        System.out.println("\nSandwich Sizes:");
        System.out.println("- Small ($3.0)");
        System.out.println("- Medium ($4.0)");
        System.out.println("- Large ($5.0)");
    }

    private static void displayDrinkOptions() {
        System.out.println("Available Drinks:");
        for (String drink : drinkNames) {
            System.out.println("- " + drink);
        }
    }

    private static void displayChipOptions() {
        System.out.println("Available Chips:");
        for (String chip : chipNames) {
            System.out.println("- " + chip);
        }
    }

    private static void displayOrder(List<Sandwich> sandwiches, List<String> drinks, List<String> chips) {
        double totalPrice = 0.0;
        System.out.println("\nYour order details:");
        for (int i = 0; i < sandwiches.size(); i++) {
            Sandwich sandwich = sandwiches.get(i);
            System.out.println("Sandwich #" + (i + 1) + ":");
            System.out.println(sandwich);
            double sandwichPrice = sandwich.calculateTotalPrice();
            totalPrice += sandwichPrice;
            System.out.println("Sandwich Price: $" + sandwichPrice);
        }
        for (String drink : drinks) {
            double drinkPrice = prices.get(drink.toLowerCase());
            System.out.println("Drink: " + drink + " ($" + drinkPrice + ")");
            totalPrice += drinkPrice;
        }
        for (String chip : chips) {
            double chipPrice = prices.get(chip.toLowerCase());
            System.out.println("Chips: " + chip + " ($" + chipPrice + ")");
            totalPrice += chipPrice;
        }
        System.out.println("Total Price: $" + totalPrice);
    }

    private static void saveOrderToFile(List<Sandwich> sandwiches, List<String> drinks, List<String> chips) {
        String fileName = generateFileName("order.txt");
        try (FileWriter writer = new FileWriter("receipts/" + fileName)) {
            writer.write("Your Order:\n");
            if (!sandwiches.isEmpty()) {
                writer.write("Sandwiches:\n");
                for (Sandwich sandwich : sandwiches) {
                    writer.write("- " + sandwich.getSize() + " " + sandwich.getBreadType() + ":\n");
                    writer.write("  Toppings: " + String.join(", ", sandwich.getToppings()) + "\n");
                    writer.write("  Extras: " + String.join(", ", sandwich.getExtras()) + "\n");
                    writer.write("  Sauces: " + String.join(", ", sandwich.getSauces()) + "\n");
                    writer.write("  Toasted: " + (sandwich.isToasted() ? "Yes" : "No") + "\n");
                }
            }
            if (!drinks.isEmpty()) {
                writer.write("Drinks:\n");
                drinks.forEach(drink -> {
                    try {
                        writer.write("- " + drink + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            if (!chips.isEmpty()) {
                writer.write("Chips:\n");
                chips.forEach(chip -> {
                    try {
                        writer.write("- " + chip + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            System.out.println("Order saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error occurred while saving the order to file.");
            e.printStackTrace();
        }
    }

    private static String generateFileName(String s) {
        String timestamp = dateFormat.format(new Date());
        return timestamp + ".txt";
    }

    private static double calculateTotalPrice(List<Sandwich> sandwiches, List<String> drinks, List<String> chips) {
        double totalPrice = 0.0;

        // Calculate total price of sandwiches
        for (Sandwich sandwich : sandwiches) {
            totalPrice += sandwich.calculateTotalPrice();
        }

        // Add price of each drink
        for (String drink : drinks) {
            totalPrice += prices.getOrDefault(drink.toLowerCase(), 0.0); // Get the price from the prices map
        }

        // Add price of each chip
        for (String chip : chips) {
            totalPrice += prices.getOrDefault(chip.toLowerCase(), 0.0); // Get the price from the prices map
        }

        return totalPrice;
    }
}


