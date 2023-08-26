package com.example.shopping_receipt;

import org.junit.Assert;
import org.junit.Test;

public class CategoryExemptionsTest {
    
    @Test
    public void testGetCategoryForProduct() {
        CategoryExemptions categoryExemptions = new CategoryExemptions("src/main/java/com/example/shoppping_receipt/data/product_categories.json");
        
        String category = categoryExemptions.getCategoryForProduct("potato chips");
        Assert.assertEquals("food", category);
        
        category = categoryExemptions.getCategoryForProduct("shirt");
        Assert.assertEquals("clothing", category);
        
        category = categoryExemptions.getCategoryForProduct("book");
        Assert.assertEquals("stationary", category);
        
        category = categoryExemptions.getCategoryForProduct("pencil");
        Assert.assertEquals("stationary", category);
        
        category = categoryExemptions.getCategoryForProduct("invalid_product");
        Assert.assertEquals("EMPTY", category);
    }
}
