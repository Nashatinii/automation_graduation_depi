package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class register_tests extends test_base {

    @Test
    public void register_valid_user() {
        home.openRegisterPage();
        String email = "automation" + System.currentTimeMillis() + "@gmail.com";
        register.registerNewUser("automation", "reg", email, "mohamed123");
        
        // Verify registration is successful
        Assert.assertTrue(register.isRegistrationSuccessful(), "Registration should be successful");
    }

    @Test
    public void register_user_with_newsletter() {
        home.openRegisterPage();
        String email = "newsletter" + System.currentTimeMillis() + "@gmail.com";
        register.registerNewUser("Test", "User", email, "password123", true);
        
        // Verify registration is successful
        Assert.assertTrue(register.isRegistrationSuccessful(), "Registration with newsletter should be successful");
    }

    @Test
    public void register_with_duplicate_email() {
        home.openRegisterPage();
        String email = "duplicate" + System.currentTimeMillis() + "@gmail.com";
        
        // Register first time
        register.registerNewUser("First", "User", email, "password123");
        Assert.assertTrue(register.isRegistrationSuccessful(), "First registration should be successful");
        
        // Try to register again with same email
        driver.get("http://localhost/opencartproject/index.php?route=account/register&language=en-gb");
        register.registerNewUser("Second", "User", email, "password123");
        
        // Verify error is displayed
        Assert.assertTrue(register.hasError(), "Error should be displayed for duplicate email");
    }

    @Test
    public void register_with_empty_fields() {
        home.openRegisterPage();
        register.clickContinue();
        
        // Verify validation errors are displayed
        Assert.assertTrue(register.isFirstNameErrorDisplayed() || register.isEmailErrorDisplayed(), 
            "Validation errors should be displayed for empty fields");
    }

    @Test
    public void register_with_invalid_email() {
        home.openRegisterPage();
        register.registerNewUser("Test", "User", "invalid-email", "password123");
        
        // Verify email validation error
        Assert.assertTrue(register.isEmailErrorDisplayed(), "Email validation error should be displayed");
    }
}
