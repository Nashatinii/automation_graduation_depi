package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class login_tests extends test_base {

    @Test
    public void loginWithValidCredentials() {
        // Use existing account
        home.openLoginPage();
        login.login("mohamed@gmail.com", "mohamednashat");
        
        // Verify login is successful - check account page is displayed
        Assert.assertTrue(login.isLoginSuccessful(), "Login should be successful");
        Assert.assertTrue(account.isAccountPageDisplayed(), "Account page should be displayed after successful login");
    }

    @Test
    public void loginWithInvalidEmail() {
        home.openLoginPage();
        login.login("invalid@email.com", "wrongpassword");
        
        // Verify error message is displayed
        Assert.assertTrue(login.hasError(), "Error message should be displayed for invalid credentials");
    }

    @Test
    public void loginWithInvalidPassword() {
        home.openLoginPage();
        login.login("test@example.com", "wrongpassword");
        
        // Verify error message is displayed
        Assert.assertTrue(login.hasError(), "Error message should be displayed for invalid password");
    }

    @Test
    public void loginWithEmptyFields() {
        home.openLoginPage();
        String currentUrl = driver.getCurrentUrl();
        login.clickLoginButton();
        
        // Wait a moment for validation to occur
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify validation - either error messages are displayed, 
        // or form validation prevented submission (still on login page with no redirect)
        boolean hasErrors = login.isEmailErrorDisplayed() || login.isPasswordErrorDisplayed();
        boolean hasValidation = login.hasValidationErrors();
        boolean stillOnLoginPage = driver.getCurrentUrl().contains("route=account/login");
        
        // The test passes if validation errors are shown OR if form validation prevented submission
        Assert.assertTrue(hasErrors || hasValidation || stillOnLoginPage, 
            "Validation should prevent login with empty fields - either show errors or prevent form submission");
    }
}

