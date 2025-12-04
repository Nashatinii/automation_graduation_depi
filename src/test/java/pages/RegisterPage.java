package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegisterPage {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;
    //                  ===== LOCATORS =====
    By firstName = By.id("input-firstname");
    By lastName = By.id("input-lastname");
    By email = By.id("input-email");
    By password = By.id("input-password");
    By newsletterCheckbox = By.id("input-newsletter");
    By agreePolicy = By.name("agree");
    By continueBtn = By.xpath("//button[@class='btn btn-primary']");
    By dublicateerrormsg = By.xpath("//*[@id=\"alert\"]/div");
    By firstNameError = By.xpath("//div[@class=\"invalid-feedback d-block\"]");
    By lastNameError = By.xpath("//div[@id=\"error-lastname\"]");
    By emailError = By.xpath("//div[@id=\"error-email\"]");
    By passwordError = By.xpath("//div[@id=\"error-password\"]");
    By acceptpolicyerror = By.xpath("//*[@id=\"alert\"]/div");
    //                  ===== Constructor ======
    public RegisterPage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver ;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));}
    //                  ===== ACTIONS =====
    public void registerNewUser(String fName, String lName, String emailValue, String pass, boolean subscribeNewsletter) {
        wait.until(ExpectedConditions.presenceOfElementLocated(firstName));
        driver.findElements(firstName).clear();
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lName);
        driver.findElements(email).clear();
        driver.findElement(email).sendKeys(emailValue);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        if(subscribeNewsletter==true){
            driver.findElement(newsletterCheckbox).click();}}
    public void agreeToPrivacyPolicy() {
        wait.until(ExpectedConditions.presenceOfElementLocated(agreePolicy));
        driver.findElement(agreePolicy).click();}
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        driver.findElement(continueBtn).click();}
    //                  ===== ASSERTION  =====
    public void Assert_dublicateacc_errormsg() {
        Assert.assertEquals(driver.findElement(dublicateerrormsg).getText(),"Warning: E-Mail Address is already registered!");
        Assert.assertEquals(driver.getCurrentUrl(),"http://localhost/opencartproject/index.php?route=account/register&language=en-gb");}
    public void Assert_reg_without_accept_policy() {
        Assert.assertEquals(driver.findElement(acceptpolicyerror).getText(),"Warning: You must agree to the Privacy Policy!");
        Assert.assertEquals(driver.getCurrentUrl(),"http://localhost/opencartproject/index.php?route=account/register&language=en-gb");}
    public void Assert_invalidacc_errormsg() {
        Assert.assertEquals(driver.getCurrentUrl(),"http://localhost/opencartproject/index.php?route=account/register&language=en-gb");}
    public void Assert_emptyfields_errors_msg() {
        Assert.assertEquals(driver.findElement(firstNameError).getText(),"First Name must be between 1 and 32 characters!");
        Assert.assertEquals(driver.findElement(lastNameError).getText(),"Last Name must be between 1 and 32 characters!");        Assert.assertEquals(driver.findElement(lastNameError).getText(),"Last Name must be between 1 and 32 characters!");
        Assert.assertEquals(driver.findElement(emailError).getText(),"E-Mail Address does not appear to be valid!");
        Assert.assertEquals(driver.findElement(passwordError).getText(),"Password must be between 6 and 40 characters!");
        Assert.assertEquals(driver.getCurrentUrl(),"http://localhost/opencartproject/index.php?route=account/register&language=en-gb");
        }

}
