package com.example.shoppping_receipt;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class CategoryExemptions {
    private JSONObject categories;

    public CategoryExemptions(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader(filePath);
            categories = (JSONObject) parser.parse(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCategoryForProduct(String productName) {
        String category = (String) categories.get(productName);
        return category != null ? category : "EMPTY";
    }
}
