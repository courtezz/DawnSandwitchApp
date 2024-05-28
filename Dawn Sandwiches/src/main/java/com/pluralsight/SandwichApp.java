package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SandwichApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Double> prices = new HashMap<>();
    private static final List<String> drinkNames = Arrays.asList("coke", "Pepsi", "dr. pepper", "sprite", "HI-C", "lemonade");
    private static final List<String> chipNames = Arrays.asList("nacho Doritos", "Cheetos", "hot Cheetos", "ranch Doritos");

    static {
        // Define the prices of toppings, cheese, and sandwich sizes
        //veggies
        prices.put("lettuce", 0.0);
        prices.put("peppers", 0.0);
        prices.put("onions", 0.0);
        prices.put("tomatoes", 0.0);
        prices.put("jalapenos", 0.0);
        prices.put("cucumbers", 0.0);
        prices.put("pickles", 0.0);
        prices.put("guacamole", 0.0);
        prices.put("mushrooms", 0.0);
          //meats
        prices.put("steak", 1.0);
        prices.put("ham", 1.0);
        prices.put("salami", 1.0);
        prices.put("roast beef", 1.0);
        prices.put("chicken", 1.0);
        prices.put("bacon", 1.0);
         //cheese
        prices.put("american", 0.5);
        prices.put("provolone", 0.5);
        prices.put("cheddar", 0.5);
        prices.put("swiss", 0.5);

        // Prices for drinks
        prices.put("coke", 1.5);
        prices.put("pepsi", 1.5);
        prices.put("dr. pepper", 1.5);
        prices.put("sprite", 1.5);
        prices.put("hi-c", 1.5);
        prices.put("lemonade", 1.5);

        // Prices for chips
        prices.put("nacho doritos", 1.0);
        prices.put("cheetos", 1.0);
        prices.put("hot cheetos", 1.0);
        prices.put("ranch doritos", 1.0);

    }

    public static void main(String[] args) {
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

        // Display Regular Toppings
        System.out.println("\nRegular Toppings:");
        prices.forEach((topping, price) -> {
            if (price == 0.0) {
                System.out.println("- " + topping + " ($" + price + ")");
            }
        });

        displayOrder(sandwiches, drinks, chips);
        saveOrderToFile(sandwiches, drinks, chips);
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

    private static Sandwich createSandwich() {
        System.out.println("Customize your sandwich:");

        System.out.println("Select your bread:");
        String breadType = getUserInput("Enter bread type (e.g., White, Wheat, Rye, Wrap): ");

        System.out.println("Select sandwich size:");
        String size = getUserInput("Enter size (e.g., Small, Medium, Large): ");

        List<String> toppings = selectOptions("Toppings");
        List<String> extras = selectOptions("Extras");
        List<String> sauces = selectOptions("Sauces");

        String toastInput = getUserInput("Would you like your sandwich toasted? (yes/no): ");
        boolean toasted = toastInput.equalsIgnoreCase("yes");

        return new Sandwich(size, breadType, toppings, extras, sauces, toasted);
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

    private static String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume invalid input
        }
        return scanner.nextInt();
    }

    private static void displayMenuOptions() {
        System.out.println("\nMenu Options:");
        System.out.println("Regular Toppings:");
        for (Map.Entry<String, Double> entry : prices.entrySet()) {
            if (entry.getValue() == 0.0) {
                System.out.println("- " + entry.getKey() + " ($" + entry.getValue() + ")");
            }
        }
        System.out.println("\nPremium Toppings:");
        for (Map.Entry<String, Double> entry : prices.entrySet()) {
            if (entry.getValue() == 1.0) {
                System.out.println("- " + entry.getKey() + " ($" + entry.getValue() + ")");
            }
        }
        System.out.println("\nCheese option:");
        for (Map.Entry<String, Double> entry : prices.entrySet()) {
            if (entry.getValue() == 0.5) {
                System.out.println("- " + entry.getKey() + " ($" + entry.getValue() + ")");
            }
        }
        System.out.println("\nSandwich Sizes:");
        System.out.println("- Small ($3.0)");
        System.out.println("- Medium ($4.0)");
        System.out.println("- Large ($5.0)");
    }

    private static void displayOrder(List<Sandwich> sandwiches, List<String> drinks, List<String> chips) {
        double totalPrice = 0.0;
        System.out.println("\nYour order details:");
        for (int i = 0; i < sandwiches.size(); i++) {
            System.out.println("Sandwich #" + (i + 1) + ":");
            System.out.println(sandwiches.get(i));
            totalPrice += sandwiches.get(i).calculateTotalPrice();
        }
        for (String drink : drinks) {
            System.out.println("Drink: " + drink);
            totalPrice += 1.50; // Assuming each drink costs $1.50
        }
        for (String chip : chips) {
            System.out.println("Chips: " + chip);
            totalPrice += 1.00; // Assuming each pack of chips costs $1.00
        }
        System.out.println("Total Price: $" + totalPrice);
    }

    private static void saveOrderToFile(List<Sandwich> sandwiches, List<String> drinks, List<String> chips) {
        try (FileWriter writer = new FileWriter("SandwichOrder.txt")) {
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
            System.out.println("Order saved to file: SandwichOrder.txt");
        } catch (IOException e) {
            System.out.println("Error occurred while saving the order to file.");
            e.printStackTrace();
        }
    }
}




