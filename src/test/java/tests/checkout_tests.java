package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class checkout_tests extends test_base {

    @Test
    public void completeCheckoutWithExistingAddress() {
        // Login first
        loginWithExistingAccount();
        
        // Wait for login
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Add product to cart
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=40");
        sim_prod.addToCart();
        
        // Wait for product to be added
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to checkout
        home.openCheckout();
        
        // Wait for checkout page
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify checkout page
        Assert.assertTrue(checkout.isCheckoutPageDisplayed(), "Checkout page should be displayed");
        
        // Select first existing address
        checkout.selectFirstExistingAddress();
        
        // Select shipping method (Free Shipping)
        checkout.selectFreeShipping();
        
        // Select payment method (Bank Transfer)
        checkout.selectBankTransfer();
        
        // Wait for all steps to complete and confirm button to be enabled
        checkout.waitForConfirmButtonToBeEnabled();
        
        // Verify order confirmation section is displayed
        Assert.assertTrue(checkout.isOrderConfirmationSectionDisplayed(), "Order confirmation section should be displayed");
        
        // Check if order total is displayed
        String orderTotal = checkout.getOrderTotal();
        Assert.assertFalse(orderTotal.isEmpty(), "Order total should be displayed");
        
        // Verify confirm button is enabled
        Assert.assertTrue(checkout.isConfirmButtonEnabled(), "Confirm Order button should be enabled");
    }

    @Test
    public void checkoutWithNewShippingAddress() {
        // Login first
        loginWithExistingAccount();
        
        // Wait for login
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Add product to cart
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=40");

        
        // Wait for product to be added
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to checkout
        home.openCheckout();
        
        // Wait for checkout page
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Fill new shipping address
        checkout.fillNewShippingAddress(
            "Test", "User", "Test Company",
            "123 Test Street", "London", "SW1A 1AA",
            "United Kingdom", "Greater London"
        );
        
        // Continue with shipping address
        checkout.clickContinueShippingAddress();
        
        // Select shipping method
        checkout.selectFreeShipping();
        
        // Select payment method
        checkout.selectBankTransfer();
        
        // Wait for all steps to complete
        checkout.waitForConfirmButtonToBeEnabled();
        
        // Verify checkout process
        Assert.assertTrue(checkout.isOrderConfirmationSectionDisplayed(), "Order confirmation section should be displayed");
        
        String orderTotal = checkout.getOrderTotal();
        Assert.assertFalse(orderTotal.isEmpty(), "Order total should be displayed");
    }

    @Test
    public void checkoutWithComment() {
        // Login first
        loginWithExistingAccount();
        
        // Wait for login
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Add product to cart
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=40");

        // Wait for product to be added
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to checkout
        home.openCheckout();
        
        // Wait for checkout page
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Select first existing address
        checkout.selectFirstExistingAddress();
        
        // Select shipping method
        checkout.selectFreeShipping();
        
        // Select payment method
        checkout.selectBankTransfer();
        
        // Add comment
        checkout.addComment("Please deliver in the morning");
        
        // Wait for comment to be saved and confirm button to be enabled
        checkout.waitForConfirmButtonToBeEnabled();
        
        // Verify order confirmation section is displayed
        Assert.assertTrue(checkout.isOrderConfirmationSectionDisplayed(), "Order confirmation section should be displayed");
        
        String orderTotal = checkout.getOrderTotal();
        Assert.assertFalse(orderTotal.isEmpty(), "Order total should be displayed");
    }

    @Test
    public void verifyCheckoutPageElements() {
        // Login first
        loginWithExistingAccount();
        
        // Wait for login
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Add product to cart
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=40");

        // Wait for product to be added
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to checkout
        home.openCheckout();
        
        // Wait for checkout page
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify checkout page elements
        Assert.assertTrue(checkout.isCheckoutPageDisplayed(), "Checkout page should be displayed");
        
        // Verify order total is displayed
        String orderTotal = checkout.getOrderTotal();
        Assert.assertFalse(orderTotal.isEmpty(), "Order total should be displayed on checkout page");
    }

    @Test
    public void selectShippingAndPaymentMethods() {
        // Login first
        loginWithExistingAccount();
        
        // Wait for login
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Add product to cart
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=40");

        // Wait for product to be added
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to checkout
        home.openCheckout();
        
        // Wait for checkout page
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Select first existing address
        checkout.selectFirstExistingAddress();
        
        // Select shipping method (Flat Rate)
        checkout.selectFlatRateShipping();
        // Select payment method (Cash on Delivery)
        checkout.selectCashOnDelivery();
        
        // Wait for all steps to complete
        checkout.waitForConfirmButtonToBeEnabled();
        
        // Verify methods were selected and checkout page is ready
        Assert.assertTrue(checkout.isCheckoutPageDisplayed(), "Checkout page should still be displayed");
        Assert.assertTrue(checkout.isOrderConfirmationSectionDisplayed(), "Order confirmation section should be displayed");
    }
    
    @Test
    public void verifyCheckoutFlowWithAllSteps() {
        // Login first
        loginWithExistingAccount();
        
        // Wait for login
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Add product to cart
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=40");

        // Wait for product to be added
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to checkout
        home.openCheckout();
        
        // Wait for checkout page
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Step 1: Select shipping address
        checkout.selectFirstExistingAddress();
        
        // Step 2: Select shipping method
        checkout.selectFreeShipping();
        
        // Step 3: Select payment method
        checkout.selectBankTransfer();
        
        // Step 4: Add comment (optional)
        checkout.addComment("Test order comment");
        
        // Wait for all steps to complete
        checkout.waitForConfirmButtonToBeEnabled();
        
        // Verify all steps completed
        Assert.assertTrue(checkout.isOrderConfirmationSectionDisplayed(), "Order confirmation should be displayed");
        Assert.assertTrue(checkout.isConfirmButtonEnabled(), "Confirm button should be enabled");
        
        String orderTotal = checkout.getOrderTotal();
        Assert.assertFalse(orderTotal.isEmpty(), "Order total should be displayed");
    }
    
    @Test
    public void guestCheckoutFlow() {
        // Ensure user is logged out
        driver.get("http://localhost/opencartproject/index.php?route=account/logout&language=en-gb");
        driver.manage().deleteAllCookies();
        driver.get("http://localhost/opencartproject/index.php?route=common/home&language=en-gb");

        addProductToCart("40");
        home.openCheckout();
        sleep(2000);

        Assert.assertTrue(checkout.isCheckoutPageDisplayed(), "Checkout page should be displayed for guest");

        checkout.selectNewAddress();
        checkout.fillNewShippingAddress(
            "Guest", "User", "Guest Company",
            "123 Guest Street", "London", "SW1A 1AA",
            "United Kingdom", "Greater London"
        );
        checkout.clickContinueShippingAddress();
        checkout.selectFreeShipping();
        checkout.selectBankTransfer();
        checkout.agreeToTerms();
        checkout.waitForConfirmButtonToBeEnabled();

        Assert.assertTrue(checkout.isConfirmButtonEnabled(), "Confirm button should be enabled for guest checkout");
    }
    
    @Test
    public void confirmOrderSuccessfully() {
        // Login first
        loginWithExistingAccount();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Add product to cart
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=40");

        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to checkout
        home.openCheckout();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        Assert.assertTrue(checkout.isCheckoutPageDisplayed(), "Checkout page should be displayed");
        
        // Complete checkout steps
        checkout.selectFirstExistingAddress();
        checkout.selectFreeShipping();
        checkout.selectBankTransfer();
        checkout.agreeToTerms();
        checkout.waitForConfirmButtonToBeEnabled();
        
        Assert.assertTrue(checkout.isConfirmButtonEnabled(), "Confirm Order button should be enabled");
        
        // Confirm order
        checkout.confirmOrder();
        
        // Verify success page
        boolean isSuccess = checkout.waitForOrderSuccess();
        Assert.assertTrue(isSuccess, "Order success page should be displayed");
        Assert.assertTrue(driver.getCurrentUrl().contains("route=checkout/success") || checkout.isOrderSuccessDisplayed(),
            "Should navigate to order success page");
    }

    private void addProductToCart(String productId) {
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=" + productId);
        sleep(2000);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

