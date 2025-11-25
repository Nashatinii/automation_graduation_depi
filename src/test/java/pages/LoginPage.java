package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // ===== LOCATORS =====
    By emailInput = By.id("input-email");
    By passwordInput = By.id("input-password");
    By loginButton = By.xpath("//button[@type='submit' and contains(@class, 'btn-primary')]");
    By forgotPasswordLink = By.xpath("//a[contains(@href, 'route=account/forgotten')]");
    By registerLink = By.xpath("//a[contains(@href, 'route=account/register')]");
    By successMessage = By.xpath("//div[contains(@class, 'alert-success')]");
    By errorMessage = By.xpath("//div[contains(@class, 'alert-danger')]");
    By emailError = By.id("error-email");
    By passwordError = By.id("error-password");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== ACTIONS =====
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.presenceOfElementLocated(emailInput));
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    public void clickForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));
        driver.findElement(forgotPasswordLink).click();
    }

    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        driver.findElement(registerLink).click();
    }

    // ===== VALIDATION METHODS =====
    public boolean isLoginSuccessful() {
        try {
            // Wait for redirect away from login page
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("route=account/login")));
            
            // Check if we're on account page (successful login)
            boolean isAccountPage = driver.getCurrentUrl().contains("route=account/account") ||
                                   driver.getCurrentUrl().contains("route=common/home");
            
            // Also check for account page title
            try {
                By accountPageTitle = By.xpath("//h1[contains(text(), 'My Account')]");
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
                shortWait.until(ExpectedConditions.presenceOfElementLocated(accountPageTitle));
                return true;
            } catch (Exception e) {
                return isAccountPage;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasError() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return driver.findElement(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isEmailErrorDisplayed() {
        try {
            WebElement error = driver.findElement(emailError);
            return error.isDisplayed() && !error.getText().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordErrorDisplayed() {
        try {
            WebElement error = driver.findElement(passwordError);
            return error.isDisplayed() && !error.getText().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasValidationErrors() {
        // Check for HTML5 validation (required attribute)
        try {
            WebElement emailField = driver.findElement(emailInput);
            WebElement passwordField = driver.findElement(passwordInput);
            
            // Check if fields have HTML5 validation
            boolean emailRequired = emailField.getAttribute("required") != null;
            boolean passwordRequired = passwordField.getAttribute("required") != null;
            
            // Check if error messages are displayed
            boolean hasErrorMessages = isEmailErrorDisplayed() || isPasswordErrorDisplayed();
            
            // Check if form submission was prevented (stayed on login page)
            boolean stillOnLoginPage = driver.getCurrentUrl().contains("route=account/login");
            
            return hasErrorMessages || (emailRequired && passwordRequired && stillOnLoginPage);
        } catch (Exception e) {
            return false;
        }
    }
}

