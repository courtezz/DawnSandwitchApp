package com.pluralsight;

import java.util.LinkedList;
import java.util.Scanner;

public class OrderScreen {
    private final LinkedList<String> orderEntries;

    public OrderScreen() {
        this.orderEntries = new LinkedList<>();
    }
    public void displayMenu() {
        while (true) {
            System.out.println("\nOrder Screen:");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Enter your choice: ");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    checkout();
                    return;
                case 0:
                    cancelOrder();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void addSandwich() {
        System.out.println("Adding a sandwich...");
        Scanner scanner = new Scanner(System.in);

        // Prompt user for sandwich details
        System.out.print("Enter sandwich type: ");
        String sandwichType = scanner.nextLine();

        // Construct the sandwich entry string
        String entry = "Sandwich: " + sandwichType;
        orderEntries.addFirst(entry);
    }

    private void addDrink() {
        System.out.println("Adding a drink...");
        Scanner scanner = new Scanner(System.in);

        // Prompt user for drink details
        System.out.print("Enter drink type: ");
        String drinkType = scanner.nextLine();

        // Construct the drink entry string
        String entry = "Drink: " + drinkType;
        orderEntries.addFirst(entry);
    }

    private void addChips() {
        System.out.println("Adding chips...");
        Scanner scanner = new Scanner(System.in);

        // Prompt user for chips details
        System.out.print("Enter chips type: ");
        String chipsType = scanner.nextLine();

        // Construct the chips entry string
        String entry = "Chips: " + chipsType;
        orderEntries.addFirst(entry);
    }

    private void checkout() {
        System.out.println("Checking out...");
        // Print order details
        displayOrderEntries();
        // Process payment, etc.
    }

    private void cancelOrder() {
        System.out.println("Canceling order...");
        orderEntries.clear();
        System.out.println("Order canceled.");
    }

    private void displayOrderEntries() {
        System.out.println("\nOrder Entries:");
        for (String entry : orderEntries) {
            System.out.println(entry);
        }
    }}