package com.example.shoppping_receipt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a shopping location: (Enter 'NY' or 'CA')");
        String locationInput = scanner.nextLine();

        Location[] locations = Location.loadLocations("src/main/java/com/example/shoppping_receipt/data/tax_rate.json");
        CategoryExemptions category_info = new CategoryExemptions("src/main/java/com/example/shoppping_receipt/data/product_categories.json");
        Location selectedLocation = null;

        if (locationInput.equalsIgnoreCase("NY")) {
            selectedLocation = locations[1];
        } else if (locationInput.equalsIgnoreCase("CA")) {
            selectedLocation = locations[0];
        } else {
            System.out.println("Invalid location input. Please choose either 'NY' or 'CA'.");
            System.exit(0);
        }

        List<Product> products = new ArrayList<>();

        while (true) {
            System.out.println("Enter product name (or 'exit' to finish): ");
            String productName = scanner.nextLine();
            
            if (productName.equalsIgnoreCase("exit")) {
                break;
            }

            // Input validation for product price
            double price = 0;
            while (price <= 0) {
                System.out.println("Enter product price (greater than 0): ");
                try {
                    price = Double.parseDouble(scanner.nextLine());
                    if (price <= 0) {
                        System.out.println("Please enter a valid price.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number for price.");
                }
            }

            // Input validation for product quantity
            int quantity = 0;
            while (quantity <= 0) {
                System.out.println("Enter product quantity (greater than 0): ");
                try {
                    quantity = Integer.parseInt(scanner.nextLine());
                    if (quantity <= 0) {
                        System.out.println("Please enter a valid quantity.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number for quantity.");
                }
            }

            products.add(new Product(productName, price, quantity));
        }

        Receipt receipt = new Receipt(selectedLocation, products);
        receipt.generateReceipt(category_info);

        scanner.close();
    }
}
