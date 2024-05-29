package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SandwichOrderingSystem {
    private final Order order;
    private final Scanner scanner;

    public SandwichOrderingSystem(Order order, Scanner scanner) {
        this.order = order;
        this.scanner = scanner;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();
        SandwichOrderingSystem system = new SandwichOrderingSystem(order, scanner);
        system.startOrderingProcess();
        scanner.close();
    }

    public void startOrderingProcess() throws IOException {
        System.out.println("Welcome to the Sandwich Ordering System!");
        while (true) {
            System.out.println("Home Screen");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) {
                System.out.println("Exiting the application...");
                break;
            } else if (choice == 1) {
                order.clear();
                handleOrder();
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void handleOrder() throws IOException {
        while (true) {
            System.out.println("Order Screen");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            int orderChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (orderChoice == 0) {
                order.clear();
                break;
            } else if (orderChoice == 1) {
                addSandwich();
            } else if (orderChoice == 2) {
                addDrink();
            } else if (orderChoice == 3) {
                addChips();
            } else if (orderChoice == 4) {
                checkoutOrder();
                break;
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void checkoutOrder() throws IOException {
        order.displayOrder();
        double totalPrice = order.calculateTotalPrice();
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("Proceed to checkout? (yes/no)");
        String confirmCheckout = scanner.nextLine().toLowerCase();
        if (confirmCheckout.equals("yes")) {
            performCheckout();
        } else {
            System.out.println("Order not confirmed. Continuing with the order.");
        }
    }

    private void performCheckout() throws IOException {
        generateReceipt();
        //saveOrderToFile();
        System.out.println("Checkout completed. Thank you for your order!");
    }





    private void generateReceipt() {
        System.out.println("Receipt generated:");
        System.out.println(order.displayOrder());
        double totalPrice = order.calculateTotalPrice();
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("Payment Method: Cash");
        System.out.println("Thank you for your purchase!");
        saveOrderToFile(order.displayOrder());
    }

    private void addChips() {
        System.out.println("Choose chips type:");
        System.out.println("1) Regular Chips ($1.00)");
        System.out.println("2) BBQ Chips ($1.25)");
        System.out.println("3) Sour Cream & Onion Chips ($1.25)");
        int chipsChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String chipsType;
        double chipsPrice;

        switch (chipsChoice) {
            case 1:
                chipsType = "Regular Chips";
                chipsPrice = 1.00;
                break;
            case 2:
                chipsType = "BBQ Chips";
                chipsPrice = 1.25;
                break;
            case 3:
                chipsType = "Sour Cream & Onion Chips";
                chipsPrice = 1.25;
                break;
            default:
                System.out.println("Invalid choice, chips set to Regular Chips.");
                chipsType = "Regular Chips";
                chipsPrice = 1.00;
                break;
        }

        order.addChips(chipsType);
        System.out.println("Chips added to the order.");
    }

    private void addDrink() {
        System.out.println("Choose drink size:");
        System.out.println("1) Small ($1.50)");
        System.out.println("2) Medium ($2.00)");
        System.out.println("3) Large ($2.50)");
        int drinkSizeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String drinkSize;
        double drinkPrice;

        switch (drinkSizeChoice) {
            case 1:
                drinkSize = "Small";
                drinkPrice = 1.50;
                break;
            case 2:
                drinkSize = "Medium";
                drinkPrice = 2.00;
                break;
            case 3:
                drinkSize = "Large";
                drinkPrice = 2.50;
                break;
            default:
                System.out.println("Invalid choice, drink size set to Small.");
                drinkSize = "Small";
                drinkPrice = 1.50;
                break;
    }
        System.out.println("Choose drink option:");
        System.out.println("1) Coke ($1.00)");
        System.out.println("2) Pepsi ($1.00)");
        System.out.println("3) Lemonade ($1.50)");
        int drinkOptionChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String drinkOption;
        double drinkOptionPrice;

        switch (drinkOptionChoice) {
            case 1:
                drinkOption = "Coke";
                drinkOptionPrice = 1.00;
                break;
            case 2:
                drinkOption = "Pepsi";
                drinkOptionPrice = 1.00;
                break;
            case 3:
                drinkOption = "Lemonade";
                drinkOptionPrice = 1.50;
                break;
            default:
                System.out.println("Invalid choice, drink option set to Coke.");
                drinkOption = "Coke";
                drinkOptionPrice = 1.00;
                break;
        }

        order.addDrink(drinkSize + " " + drinkOption);
        System.out.println("Drink added to the order.");
    }
    private void addSandwich() {
        while (true) {
            System.out.println("\nWould you like to order a sandwich? (yes/no)");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("no")) {
                break;
            }
            System.out.println("Customizing sandwich:");
            System.out.println("Choose sandwich size (4/8/12 inches):");
            String size = scanner.nextLine();
            System.out.println("Choose bread type (white/wheat/rye/wrap):");
            String bread = scanner.nextLine();
            Sandwich sandwich = new Sandwich(size, bread);
            System.out.println("Would you like it toasted? (yes/no)");
            String toastedChoice = scanner.nextLine().toLowerCase();
            sandwich.setToasted(toastedChoice.equals("yes"));

            // Display regular and premium topping options with prices
            displayToppingOptions();

            System.out.println("Add regular toppings (comma-separated, enter 'done' when finished):");
            String regularToppingsInput = scanner.nextLine();
            if (!regularToppingsInput.equals("done")) {
                String[] regularToppings = regularToppingsInput.split(",");
                for (String topping : regularToppings) {
                    sandwich.addRegularTopping(topping.trim());
                }
            }

            System.out.println("Add premium toppings (comma-separated, enter 'done' when finished):");
            String premiumToppingsInput = scanner.nextLine();
            if (!premiumToppingsInput.equals("done")) {
                String[] premiumToppings = premiumToppingsInput.split(",");
                for (String topping : premiumToppings) {
                    // Check if the topping is a premium topping
                    if (isPremiumTopping(topping.trim())) {
                        System.out.println("Premium topping '" + topping.trim() + "' added (Additional cost applied).");
                        sandwich.addPremiumTopping(topping.trim());
                    } else {
                        sandwich.addRegularTopping(topping.trim());
                    }
                }
            }

            order.addSandwich(sandwich);
        }
    }

    private boolean isPremiumTopping(String topping) {
        // Define your list of premium toppings here
        String[] premiumToppings = {"Bacon", "Avocado", "Pepper Jack Cheese", "Jalapenos"};
        for (String premium : premiumToppings) {
            if (topping.equalsIgnoreCase(premium)) {
                return true;
            }
        }
        return false;
    }

    private void displayToppingOptions() {
        System.out.println("Regular Toppings (price per topping):");
        System.out.println("- Lettuce ($0.50)");
        System.out.println("- Tomato ($0.50)");
        System.out.println("- Onion ($0.50)");
        System.out.println("- Pickles ($0.50)");
        System.out.println("Premium Toppings (price per topping):");
        System.out.println("- Bacon ($1.50)");
        System.out.println("- Avocado ($1.00)");
        System.out.println("- Pepper Jack Cheese ($1.00)");
        System.out.println("- Jalapenos ($0.75)");
    }
    private void saveOrderToFile(String orderSummary) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String finalDate = date.format(formatter);
        String pathThrough = "src/main/resources/dawnApp/" + finalDate + ".txt";
        try {
            FileWriter writer = new FileWriter(pathThrough);
            writer.write(orderSummary);
            writer.close();
            System.out.println("Order has been saved to ");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the order.");
            e.printStackTrace();
        }
    }
}
