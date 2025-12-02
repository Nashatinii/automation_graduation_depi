package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class login_tests extends test_base {

    @Test
    public void loginWithValidCredentials() {
        loginWithExistingAccount();
        account.Assertion_login_invalidemail();
    }
    @Test
    public void loginWithInvalidEmail() {
        home.openLoginPage();
        login.login("invalid@email.com", "wrongpassword");
        login.click_login_btn();
        login.Assertion_login_invalidemail();
    }

    @Test
    public void loginWithInvalidPassword() {
        home.openLoginPage();
        login.login("testuser2@gmail.com", "wrongpassword");
        login.click_login_btn();
        login.Assertion_login_invalidpass();
    }
    @Test
    public void loginWithEmptyFields() {
        home.openLoginPage();
        login.login("", "");
        login.click_login_btn();
        login.Assertion_login_emptyfields();
    }
    @Test
    public void loginwithinvalidpass_7times() {
        home.openLoginPage();
        login.login("testuser2@gmail.com", "wrongpassword");
        login.click_login_btn();
        login.click_login_btn();
        login.click_login_btn();
        login.click_login_btn();
        login.click_login_btn();
        login.click_login_btn();
        login.click_login_btn();
        login.Assertion_invalidlogin_manytimes();
    }
}

