package com.example.shoppping_receipt;

import java.util.List;

public class Receipt {
    private Location location;
    private List<Product> products;
    
    public Receipt(Location location, List<Product> products) {
        this.location = location;
        this.products = products;
    }

    public void generateReceipt(CategoryExemptions category_info) {
        System.out.println("item\t\t\t\tprice\t\tqty");

        double subtotal = 0.0;
        double totalTax = 0.0;

        for (Product product : products) {
            String category = category_info.getCategoryForProduct(product.getName());
            double tax = location.calculateSalesTax(product.getPrice(), product.getQuantity(), category, product.getName());
            double totalProductPrice = product.getTotalPrice() + tax;

            System.out.printf("%s \t\t\t\t $%.2f \t %d%n", product.getName(), totalProductPrice, product.getQuantity());

            subtotal += product.getTotalPrice();
            totalTax += tax;
        }

        System.out.printf("subtotal:%33s $%.2f%n", "", subtotal);
        System.out.printf("tax:%38s $%.2f%n", "", totalTax);
        System.out.printf("total:%36s $%.2f%n", "", subtotal + totalTax);
    }
}
