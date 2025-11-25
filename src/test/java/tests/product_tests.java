package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class product_tests extends test_base {

    @Test
    public void addProductToCart() {
        // Navigate to a product page (you can modify this URL based on your products)
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=43");
        
        // Add product to cart
        product.addToCart();
        
        // Verify product was added successfully
        Assert.assertTrue(product.isAddToCartSuccessful(), "Product should be added to cart successfully");
    }

    @Test
    public void addProductToCartWithQuantity() {
        // Navigate to a product page
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=43");
        
        // Add product to cart with quantity 2
        product.addToCartWithQuantity(2);
        // Verify product was added successfully
        Assert.assertTrue(product.isAddToCartSuccessful(), "Product should be added to cart with specified quantity");
    }

    @Test
    public void addProductToWishlist() {
        // Login with existing account
        loginWithExistingAccount();
        
        // Navigate to a product page
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=43");
        
        // Add to wishlist
        product.addToWishlist();
        
        // Verify (you can add more specific verification)
        Assert.assertNotNull(product.getProductName(), "Product name should be displayed");
    }

    @Test
    public void addProductWithRequiredOptionsToCart() {
        // Navigate to a product that requires option selection (e.g., Canon EOS 5D)
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=30");

        // Fill required options and add to cart
        product.addToCartWithDefaultOptions();

        // Verify product with options was added successfully
        Assert.assertTrue(product.isAddToCartSuccessful(), "Product with required options should be added to cart successfully");
    }
}

