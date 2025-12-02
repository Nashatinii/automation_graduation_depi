package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class account_tests extends test_base {
    @Test
    public void logoutFromAccountPage() {
        loginWithExistingAccount();
        account.clickLogout();
        logout.Assertion_logout();
    }
   /* @Test
    public void navigateToOrderHistory() {
        // Login with existing account
        home.openLoginPage();
        login.login("mohamed@gmail.com", "mohamednashat");
        
        // Wait for login to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to order history
        account.clickOrderHistory();
        
        // Wait for navigation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify we're on order history page
        Assert.assertTrue(driver.getCurrentUrl().contains("account/order"), 
            "Should be on order history page");
    }

    @Test
    public void navigateToWishlist() {
        // Login with existing account
        home.openLoginPage();
        login.login("mohamed@gmail.com", "mohamednashat");
        
        // Wait for login to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to wishlist
        account.clickWishlist();
        
        // Wait for navigation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify we're on wishlist page
        Assert.assertTrue(driver.getCurrentUrl().contains("account/wishlist"),
            "Should be on wishlist page");
    }

    @Test
    public void navigateToEditAccount() {
        // Login with existing account
        home.openLoginPage();
        login.login("mohamed@gmail.com", "mohamednashat");
        
        // Wait for login to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to edit account
        account.clickEditAccount();
        
        // Wait for navigation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify we're on edit account page
        Assert.assertTrue(driver.getCurrentUrl().contains("account/edit"), 
            "Should be on edit account page");
    }

    @Test
    public void navigateToChangePassword() {
        // Login with existing account
        home.openLoginPage();
        login.login("mohamed@gmail.com", "mohamednashat");
        
        // Wait for login to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to change password
        account.clickChangePassword();
        
        // Wait for navigation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify we're on change password page
        Assert.assertTrue(driver.getCurrentUrl().contains("account/password"), 
            "Should be on change password page");
    }
    @Test
    public void navigateToAddressBook() {
        // Login with existing account
        home.openLoginPage();
        login.login("mohamed@gmail.com", "mohamednashat");
        
        // Wait for login to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to address book
        account.clickAddressBook();
        
        // Wait for navigation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify we're on address book page
        Assert.assertTrue(driver.getCurrentUrl().contains("account/address"), 
            "Should be on address book page");
    }

    @Test
    public void navigateToNewsletter() {
        // Login with existing account
        home.openLoginPage();
        login.login("mohamed@gmail.com", "mohamednashat");
        
        // Wait for login to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Navigate to newsletter
        account.clickNewsletter();
        
        // Wait for navigation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify we're on newsletter page
        Assert.assertTrue(driver.getCurrentUrl().contains("account/newsletter"), 
            "Should be on newsletter page");
    }*/
}

