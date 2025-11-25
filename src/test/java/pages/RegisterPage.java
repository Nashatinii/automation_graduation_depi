package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    WebDriver driver;
    WebDriverWait wait;

    // ===== LOCATORS =====
    By firstName = By.id("input-firstname");
    By lastName = By.id("input-lastname");
    By email = By.id("input-email");
    By password = By.id("input-password");
    By newsletterCheckbox = By.id("input-newsletter");
    By agreePolicy = By.name("agree");
    By continueBtn = By.xpath("//button[@class='btn btn-primary']");
    By successMessage = By.xpath("//div[contains(@class, 'alert-success')]");
    By errorMessage = By.xpath("//div[contains(@class, 'alert-danger')]");
    By firstNameError = By.id("error-firstname");
    By lastNameError = By.id("error-lastname");
    By emailError = By.id("error-email");
    By passwordError = By.id("error-password");

    // Constructor
    public RegisterPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== ACTIONS =====
    public void registerNewUser(String fName, String lName, String emailValue, String pass) {
        registerNewUser(fName, lName, emailValue, pass, false);
    }

    public void registerNewUser(String fName, String lName, String emailValue, String pass, boolean subscribeNewsletter) {
        enterFirstName(fName);
        enterLastName(lName);
        enterEmail(emailValue);
        enterPassword(pass);
        
        if (subscribeNewsletter) {
            subscribeToNewsletter();
        }
        
        agreeToPrivacyPolicy();
        clickContinue();
    }

    public void enterFirstName(String firstNameValue) {
        wait.until(ExpectedConditions.presenceOfElementLocated(firstName));
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(firstNameValue);
    }

    public void enterLastName(String lastNameValue) {
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lastNameValue);
    }

    public void enterEmail(String emailValue) {
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(emailValue);
    }

    public void enterPassword(String passwordValue) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(passwordValue);
    }

    public void subscribeToNewsletter() {
        wait.until(ExpectedConditions.presenceOfElementLocated(newsletterCheckbox));
        WebElement newsletter = driver.findElement(newsletterCheckbox);
        
        if (!newsletter.isSelected()) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(newsletterCheckbox));
                newsletter.click();
            } catch (Exception e) {
                // Fallback to JavaScript click if regular click fails
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newsletter);
            }
        }
    }

    public void unsubscribeFromNewsletter() {
        wait.until(ExpectedConditions.presenceOfElementLocated(newsletterCheckbox));
        WebElement newsletter = driver.findElement(newsletterCheckbox);
        
        if (newsletter.isSelected()) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(newsletterCheckbox));
                newsletter.click();
            } catch (Exception e) {
                // Fallback to JavaScript click if regular click fails
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newsletter);
            }
        }
    }

    public void agreeToPrivacyPolicy() {
        wait.until(ExpectedConditions.presenceOfElementLocated(agreePolicy));
        WebElement agreeCheckbox = driver.findElement(agreePolicy);
        
        // Scroll element into view to avoid click interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", agreeCheckbox);
        
        // Wait a bit for scroll to complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Use JavaScript click if regular click fails
        if (!agreeCheckbox.isSelected()) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(agreePolicy));
                agreeCheckbox.click();
            } catch (Exception e) {
                // Fallback to JavaScript click if regular click fails
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", agreeCheckbox);
            }
        }
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        driver.findElement(continueBtn).click();
    }

    // ===== VALIDATION METHODS =====
    public boolean isRegistrationSuccessful() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessage() {
        try {
            return driver.findElement(successMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean hasError() {
        try {
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

    public boolean isFirstNameErrorDisplayed() {
        try {
            WebElement error = driver.findElement(firstNameError);
            return error.isDisplayed() && !error.getText().isEmpty();
        } catch (Exception e) {
            return false;
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
}
