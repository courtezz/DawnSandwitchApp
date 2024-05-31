package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SandwichOrderingSystem {
    private final Order order;  // Stores the current order
    private final Scanner scanner;  // Scanner object to take user input
    private double totalPrice;  // Stores the total price of the order

    public SandwichOrderingSystem(Order order, Scanner scanner) {
        this.order = order;
        this.scanner = scanner;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);  // Create a scanner to take user input from console
        Order order = new Order();  // Create a new order object
        SandwichOrderingSystem system = new SandwichOrderingSystem(order, scanner);  // Create a new SandwichOrderingSystem object
        system.startOrderingProcess();  // Start the ordering process
        scanner.close();  // Close the scanner after the ordering process is finished
    }

    // Method to start the ordering process
    public void startOrderingProcess() throws IOException {
        System.out.println("Welcome to the Sandwich Ordering System!");
        while (true) {
            System.out.println("Home Screen");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            int choice = scanner.nextInt();  // Read user choice
            scanner.nextLine(); // Consume newline

            if (choice == 0) {  // If choice is 0, exit the application
                System.out.println("Thank you for choosing Dawn Sandwich App!");
                System.out.println("Exiting the application...");
                break;
            } else if (choice == 1) {  // If choice is 1, start a new order
                order.clear();  // Clear the previous order
                handleOrder();  // Proceed to handle the new order
            } else {  // If choice is neither 0 nor 1, prompt user to enter a valid choice
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Method to handle the ordering process
    private void handleOrder() throws IOException {
        while (true) {
            System.out.println("Order Screen");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            int orderChoice = scanner.nextInt();  // Read user choice
            scanner.nextLine(); // Consume newline

            if (orderChoice == 0) {  // If choice is 0, cancel the order
                order.clear();  // Clear the order
                break;
            } else if (orderChoice == 1) {  // If choice is 1, add a sandwich to the order
                addSandwich();
            } else if (orderChoice == 2) {  // If choice is 2, add a drink to the order
                addDrink();
            } else if (orderChoice == 3) {  // If choice is 3, add chips to the order
                addChips();
            } else if (orderChoice == 4) {  // If choice is 4, proceed to checkout
                checkoutOrder();
                break;
            } else {  // If choice is none of the above, prompt user to enter a valid choice
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Method to handle the checkout process
    private void checkoutOrder() throws IOException {
        order.displayOrder();  // Display the order details
        double totalPrice = order.calculateTotalPrice();  // Calculate the total price of the order
        System.out.println("Total Price: $" + totalPrice);  // Display the total price to the user
        System.out.println("Proceed to checkout? (yes/no)");  // Prompt user to confirm checkout
        String confirmCheckout = scanner.nextLine().toLowerCase();  // Read user input
        if (confirmCheckout.equals("yes")) {  // If user confirms checkout
            performCheckout();  // Proceed with the checkout process
        } else {  // If user does not confirm checkout
            System.out.println("Order not confirmed. Continuing with the order.");
        }
    }

    // Method to perform the checkout process
    private void performCheckout() throws IOException {
        generateReceipt();  // Generate a receipt for the order
        System.out.println("Checkout completed. Thank you for your order!");  // Display checkout completion message
    }

    // Method to generate a receipt for the order
    private void generateReceipt() {
        System.out.println("Receipt generated:");  // Display receipt header
        System.out.println(order.displayOrder());  // Display order details
        double totalPrice = order.calculateTotalPrice();  // Calculate total price of the order
        System.out.println("Total Price: $" + totalPrice);  // Display total price to the user
        System.out.println("Payment Method: Cash");  // Display payment method
        System.out.println("Thank you for your purchase!");  // Display thank you message
        saveOrderToFile(order.displayOrder());  // Save the order details to a file
    }

    // Method to add chips to the order
    private void addChips() {
        // Display options for chips
        System.out.println("Choose chips type:");
        System.out.println("1) Regular Chips ($1.00)");
        System.out.println("2) BBQ Chips ($1.25)");
        System.out.println("3) Sour Cream & Onion Chips ($1.25)");

        int chipsChoice = scanner.nextInt();  // Read user choice
        scanner.nextLine(); // Consume newline

        // Get quantity of chips
        System.out.println("Enter quantity of chips:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String chipsType;
        double chipsPrice;

        // Assign chips type and price based on user choice
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

        // Add chips to the order
        for (int i = 0; i < quantity; i++) {
            order.addChips(chipsType);
        }
        System.out.println(quantity + " chip(s) added to the order.");
    }

    // Method to add drink to the order
    private void addDrink() {
        // Display options for drink size
        System.out.println("Choose drink size:");
        System.out.println("1) Small ($1.50)");
        System.out.println("2) Medium ($2.00)");
        System.out.println("3) Large ($2.50)");

        int drinkSizeChoice = scanner.nextInt();  // Read user choice
        scanner.nextLine(); // Consume newline

        // Get quantity of drinks
        System.out.println("Enter quantity of drinks:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String drinkSize;
        double drinkPrice;

        // Assign drink size and price based on user choice
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
        // Add drinks to the order
        for (int i = 0; i < quantity; i++) {
            order.addDrink(drinkSize);
        }
        System.out.println(quantity + " drink(s) added to the order.");

        // Display options for drink type
        System.out.println("Choose drink option:");
        System.out.println("1) Coke ($1.00)");
        System.out.println("2) Pepsi ($1.00)");
        System.out.println("3) Lemonade ($1.50)");

        int drinkOptionChoice = scanner.nextInt();  // Read user choice
        scanner.nextLine(); // Consume newline

        String drinkOption;

        // Assign drink option based on user choice
        switch (drinkOptionChoice) {
            case 1:
                drinkOption = "Coke";
                break;
            case 2:
                drinkOption = "Pepsi";
                break;
            case 3:
                drinkOption = "Lemonade";
                break;
            default:
                System.out.println("Invalid choice, drink option set to Coke.");
                drinkOption = "Coke";
                break;
        }

        order.addDrink(drinkSize + " " + drinkOption);  // Add drink to the order
        System.out.println("Drink added to the order.");
    }

    // Method to add sandwich to the order
    private void addSandwich() {
        while (true) {
            System.out.println("\nWould you like to order a sandwich? (yes/no)");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("no")) {
                break;
            } else if (choice.equals("yes")) {
                // Display options for sandwich type
                System.out.println("Choose sandwich type:");
                System.out.println("1) BLT");
                System.out.println("2) Philly Cheese Steak");
                System.out.println("3) Custom Sandwich");

                int sandwichChoice = scanner.nextInt();  // Read user choice
                scanner.nextLine(); // Consume newline

                Sandwich sandwich = null;
                switch (sandwichChoice) {
                    case 1:
                        sandwich = createBLTSandwich();
                        break;
                    case 2:
                        sandwich = createPhillyCheeseSteakSandwich();
                        break;
                    case 3:
                        sandwich = createCustomSandwich();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                }
                order.addSandwich(sandwich);  // Add sandwich to the order
            }
        }
    }

    // Method to create a custom sandwich
    private Sandwich createCustomSandwich() {
        // Prompt user to customize sandwich
        System.out.println("Creating custom sandwich:");
        System.out.println("Choose sandwich size (4/8/12 inches):");
        String size = scanner.nextLine();
        System.out.println("Choose bread type (white/wheat/rye/wrap):");
        String bread = scanner.nextLine();
        Sandwich sandwich = new Sandwich(size, bread);
        System.out.println("Would you like it toasted? (yes/no)");
        String toastedChoice = scanner.nextLine().toLowerCase();
        sandwich.setToasted(toastedChoice.equals("yes"));

        displayToppingOptions();  // Display topping options to the user

        // Add regular toppings
        System.out.println("Add regular toppings (enter toppings separate with Comma, press Enter when finished):");
        String regularToppingsInput = scanner.nextLine();
        String[] regularToppings = regularToppingsInput.split(",");
        for (String topping : regularToppings) {
            sandwich.addRegularTopping(topping.trim());
        }

// Add premium toppings
        System.out.println("Add premium toppings (enter toppings separate with Comma, press Enter when finished):");
        String premiumToppingsInput = scanner.nextLine();
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

// Add sauces
        System.out.println("Add sauces (enter sauces separate with Comma, press Enter when finished):");
        String saucesInput = scanner.nextLine();
        String[] sauces = saucesInput.split(",");
        for (String sauce : sauces) {
            sandwich.addSauce(sauce.trim());
        }



        // Ask if the user wants to add extra meat
        System.out.println("Would you like to add extra meat? (yes/no)");
        String extraMeatChoice = scanner.nextLine().toLowerCase();
        if (extraMeatChoice.equals("yes")) {
            System.out.println("Extra meat added (Additional cost applied).");
            sandwich.setExtraMeat(true);
            // Apply additional charge for extra meat
            // Add the price of extra meat to the total price of the order
            totalPrice += 2.00; // Assuming an additional charge of $2.00 for extra meat
        }

        // Ask if the user wants to add extra cheese
        System.out.println("Would you like to add extra cheese? (yes/no)");
        String extraCheeseChoice = scanner.nextLine().toLowerCase();
        if (extraCheeseChoice.equals("yes")) {
            System.out.println("Extra cheese added (Additional cost applied).");
            sandwich.setExtraCheese(true);
            // Apply additional charge for extra cheese
            // Add the price of extra cheese to the total price of the order
            totalPrice += 0.75; // Assuming an additional charge of $0.75 for extra cheese

            // Let the user choose the type of cheese
            System.out.println("Choose cheese option (Cheddar/Swiss/Provolone):");
            String cheeseInput = scanner.nextLine().trim();
            sandwich.setCheese(cheeseInput); // Set the chosen cheese for the sandwich
        }

        return sandwich;
    }

    // Method to create a Philly Cheese Steak sandwich
    private Sandwich createPhillyCheeseSteakSandwich() {
        // Create a Philly Cheese Steak sandwich with default toppings
        Sandwich sandwich = new Sandwich("8", "White");
        sandwich.setToasted(true);
        sandwich.addPremiumTopping("Steak");
        sandwich.addPremiumTopping("American Cheese");
        sandwich.addRegularTopping("Peppers");
        sandwich.addRegularTopping("Mayo");

        // Ask if the user wants to customize toppings
        System.out.println("Would you like to customize your Philly Cheese Steak sandwich toppings? (yes/no)");
        String customizeChoice = scanner.nextLine().toLowerCase();
        if (customizeChoice.equals("yes")) {
            customizeToppings(sandwich);
        }

        return sandwich;
    }

    // Method to create a BLT sandwich
    private Sandwich createBLTSandwich() {
        // Create a BLT sandwich with default toppings
        Sandwich sandwich = new Sandwich("8", "White");
        sandwich.setToasted(true);
        sandwich.addRegularTopping("Bacon");
        sandwich.addRegularTopping("Cheddar");
        sandwich.addRegularTopping("Lettuce");
        sandwich.addRegularTopping("Tomato");
        sandwich.addRegularTopping("Ranch");

        // Ask if the user wants to customize toppings
        System.out.println("Would you like to customize your BLT sandwich toppings? (yes/no)");
        String customizeChoice = scanner.nextLine().toLowerCase();
        if (customizeChoice.equals("yes")) {
            customizeToppings(sandwich);
        }

        return sandwich;
    }

    // Method to customize toppings of a sandwich
    private void customizeToppings(Sandwich sandwich) {
        // Display current toppings
        System.out.println("Current toppings:");
        System.out.println("Size: " + sandwich.getSize() + " inches");
        System.out.println("Bread: " + sandwich.getBread());
        System.out.println("Toasted: " + (sandwich.isToasted() ? "Yes" : "No"));
        System.out.println("Toppings:");
        for (String topping : sandwich.getRegularToppings()) {
            System.out.println("- " + topping);
        }
        for (String topping : sandwich.getPremiumToppings()) {
            System.out.println("- " + topping);
        }

        // Ask if the user wants to add or remove toppings
        System.out.println("Would you like to add or remove toppings? (add/remove/no)");
        String actionChoice = scanner.nextLine().toLowerCase();
        if (actionChoice.equals("add")) {
            System.out.println("Enter toppings to add (comma-separated):");
            String toppingsToAdd = scanner.nextLine();
            String[] toppings = toppingsToAdd.split(",");
            for (String topping : toppings) {
                sandwich.addRegularTopping(topping.trim());
            }
        } else if (actionChoice.equals("remove")) {
            System.out.println("Enter toppings to remove (comma-separated):");
            String toppingsToRemove = scanner.nextLine();
            String[] toppings = toppingsToRemove.split(",");
            for (String topping : toppings) {
                sandwich.removeTopping(topping.trim());
            }
        }
    }

    // Method to check if a topping is premium
    private boolean isPremiumTopping(String topping) {
        // Define list of premium toppings
        String[] premiumToppings = {"Bacon", "Avocado", "Jalapenos", "chicken", "steak", "ham", "salami", "roast beef "};
        for (String premium : premiumToppings) {
            if (topping.equalsIgnoreCase(premium)) {
                return true;
            }
        }
        return false;
    }

    // Method to display topping options
    private void displayToppingOptions() {
        // regular toppings
        System.out.println("Regular Toppings (price per topping):");
        System.out.println("- Lettuce ($0.50)");
        System.out.println("- Tomato ($0.50)");
        System.out.println("- Onion ($0.50)");
        System.out.println("- Pickles ($0.50)");

        //premium toppings
        System.out.println("Premium Toppings (price per topping):");
        System.out.println("- Bacon ($1.50)");
        System.out.println("- Avocado ($1.00)");
        System.out.println("- Jalapenos ($0.75)");
        System.out.println("- Steak ($2.00)");
        System.out.println("- Ham ($1.50)");
        System.out.println("- Salami ($1.25)");
        System.out.println("- Roast Beef ($2.00)");
        System.out.println("- Chicken ($1.75)");

        //cheese
        System.out.println("Cheese Options (price per cheese):");
        System.out.println("- Cheddar ($0.75)");
        System.out.println("- Swiss ($0.75)");
        System.out.println("- Provolone ($0.75)");
    }

    // Method to save the order to a file
    private void saveOrderToFile(String orderSummary) {
        LocalDateTime date = LocalDateTime.now();  // Get current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String finalDate = date.format(formatter);  // Format date
        String pathThrough = "src/main/resources/dawnApp/" + finalDate + ".txt";  // Path to save file
        try {
            FileWriter writer = new FileWriter(pathThrough);  // Create FileWriter object
            writer.write(orderSummary);  // Write order summary to file
            writer.close();  // Close the writer
            System.out.println("Order has been saved to " + pathThrough);  // Print success message
        } catch (IOException e) {
            System.out.println("An error occurred while saving the order.");  // Print error message
            e.printStackTrace();  // Print stack trace
        }
    }
}
