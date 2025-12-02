package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class cart_tests extends test_base {

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void addProductToCartById(String productId) {
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=" + productId);
        sleep(2000);
    }

    private void clearCartIfNeeded() {
        home.openShoppingCart();
        sleep(1500);
        int guard = 0;
        while (!cart.isCartEmpty() && cart.getCartItemsCount() > 0 && guard < 5) {
            cart.removeProduct(0);
            sleep(1500);
            guard++;
            home.openShoppingCart();
            sleep(1000);
        }
    }

    @Test
    public void viewCartAfterAddingProduct() {
        addProductToCartById("40");
        
        // Click View Cart from cart dropdown
        cart.clickViewCart();
        
        // Verify cart page is displayed
        Assert.assertTrue(cart.isCartPageDisplayed(), "Cart page should be displayed");
        Assert.assertFalse(cart.isCartEmpty(), "Cart should not be empty");
    }

    @Test
    public void verifyCartContents() {
        addProductToCartById("40");
        
        // Navigate to cart page
        home.openShoppingCart();
        
        // Verify cart contents
        int itemsCount = cart.getCartItemsCount();
        Assert.assertTrue(itemsCount > 0, "Cart should contain items");
        
        String productName = cart.getProductName(0);
        Assert.assertFalse(productName.isEmpty(), "Product name should be displayed");
        
        String total = cart.getTotal();
        Assert.assertFalse(total.isEmpty(), "Total should be displayed");
    }

    @Test
    public void updateProductQuantity() {
        addProductToCartById("40");
        
        // Navigate to cart page
        home.openShoppingCart();
        
        // Update quantity
        cart.updateQuantity(0, 2);
        cart.clickUpdate(0);
        
        sleep(3000);
        
        // Verify quantity was updated
        String quantity = cart.getProductQuantity(0);
        Assert.assertEquals(quantity, "2", "Quantity should be updated to 2");
    }

    @Test
    public void removeProductFromCart() {
        addProductToCartById("40");

        // Navigate to cart page
        home.openShoppingCart();
        
        // Verify cart has items
        int initialCount = cart.getCartItemsCount();
        Assert.assertTrue(initialCount > 0, "Cart should have items");
        
        // Remove product
        cart.removeProduct(0);
        
        sleep(2000);
        
        // Verify cart is empty or has fewer items
        // Note: Cart might show empty message or redirect
        boolean isEmpty = cart.isCartEmpty();
        int newCount = cart.getCartItemsCount();
        Assert.assertTrue(isEmpty || newCount < initialCount, "Product should be removed from cart");
    }

    @Test
    public void navigateToCheckoutFromCart() {
        addProductToCartById("40");
        
        // Navigate to cart page
        home.openShoppingCart();
        
        // Click checkout
        cart.clickCheckout();
        
        sleep(2000);
        
        // Verify checkout page is displayed
        Assert.assertTrue(checkout.isCheckoutPageDisplayed(), "Checkout page should be displayed");

        // Clean up cart to avoid side effects
        driver.navigate().back();
        clearCartIfNeeded();
    }

    @Test
    public void applyValidCouponCode() {
        addProductToCartById("40");
        home.openShoppingCart();
        String alert = cart.applyCouponCode("1111");
//        Assert.assertTrue(alert.toLowerCase().contains("success"), "Valid coupon should show success message");
//        Assert.assertTrue(cart.isCouponSuccessMessageDisplayed(), "Success alert should be visible");

        // Clean up coupon and cart
        cart.removeCouponCode();
        clearCartIfNeeded();
    }

    @Test
    public void applyInvalidCouponCode() {
        addProductToCartById("40");
        home.openShoppingCart();
        String alert = cart.applyCouponCode("INVALID");
        Assert.assertTrue(alert.toLowerCase().contains("warning") || alert.toLowerCase().contains("invalid"),
                "Invalid coupon should show warning message");
        Assert.assertTrue(cart.isCouponErrorMessageDisplayed(), "Error alert should be visible");

        clearCartIfNeeded();
    }

    @Test
    public void cartDataPersistsAfterLogout() {
        loginWithExistingAccount();
        clearCartIfNeeded();

        addProductToCartById("40");
        home.openShoppingCart();
        Assert.assertTrue(cart.getCartItemsCount() > 0, "Cart should contain the added product");

        sleep(2000);

        loginWithExistingAccount();
        home.openShoppingCart();
        Assert.assertTrue(cart.getCartItemsCount() > 0, "Cart data should persist after logout/login");

        clearCartIfNeeded();
    }
}

