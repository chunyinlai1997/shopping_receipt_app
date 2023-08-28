package com.example.shoppping_receipt;

import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONTokener;
import org.json.JSONObject;

class Location {
    //private String name;
    private double salesTaxRate;
    private JSONArray exemptCategories;

    public Location(String name, double salesTaxRate, JSONArray exemptCategories) {
        //this.name = name;
        this.salesTaxRate = salesTaxRate;
        this.exemptCategories = exemptCategories;
    }

    public static Location[] loadLocations(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            JSONArray locationsArray = new JSONArray(new JSONTokener(fileReader));
            
            Location[] locations = new Location[locationsArray.length()];
            for (int i = 0; i < locationsArray.length(); i++) {
                JSONObject locationObj = locationsArray.getJSONObject(i);
                String name = locationObj.getString("name");
                double salesTaxRate = locationObj.getDouble("salesTaxRate");
                JSONArray exemptCategories = locationObj.getJSONArray("exemptCategories");
                locations[i] = new Location(name, salesTaxRate, exemptCategories);
            }
            return locations;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public double calculateSalesTax(double price, int quantity, String category, String productName) {
        double totalPrice = price * quantity;
        if (isExempt(category, productName)) {
            return 0.0;
        }
        double rawTax = totalPrice * salesTaxRate;
        double roundedTax = Math.ceil(rawTax / 0.05) * 0.05;
        return roundedTax;
    }

    private boolean isExempt(String category, String productName) {
        for (int i = 0; i < exemptCategories.length(); i++) {
            String exemptCategory = exemptCategories.getString(i);
            if (exemptCategory.equals(category) || exemptCategory.equals(productName)) {
                return true;
            }
        }
        return false;
    }
}
