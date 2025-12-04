package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class AccountPage {
    WebDriver driver;
    WebDriverWait wait;
    //                  ===== LOCATORS =====
    By homepage = By.xpath("//img[@title=\"Your Store\"]");
    By pageTitle = By.xpath("//*[@id=\"content\"]/h1");
    By editAccountLink = By.linkText("Edit your account information");
    By changePasswordLink = By.linkText("Change your password");
    By addressBookLink = By.linkText("Modify your address book entries");
    By wishlistLink = By.linkText("Modify your wish list");
    By orderHistoryLink = By.linkText("View your order history");
    By newsletterLink = By.linkText("Subscribe / unsubscribe to newsletter");
    By logoutLink = By.linkText("Logout");
    By myAccountMenu = By.xpath("//i[@class=\"fa-solid fa-user\"]");
    By myaccbtn = By.xpath("//*[@id=\"top\"]/div/div/div[2]/ul/li[2]/div/ul/li[1]/a");
    //                  ===== Constructor ======
    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //                  ===== ACTIONS =====
    public void open_hmpage(){
        wait.until(ExpectedConditions.elementToBeClickable(pageTitle));
        driver.findElement(homepage).click();
    }
    public void clickEditAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(editAccountLink));
        driver.findElement(editAccountLink).click();}
    public void clickChangePassword() {
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordLink));
        driver.findElement(changePasswordLink).click();}
    public void clickAddressBook() {
        wait.until(ExpectedConditions.elementToBeClickable(addressBookLink));
        driver.findElement(addressBookLink).click();}
    public void clickWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(wishlistLink));
        driver.findElement(wishlistLink).click();}
    public void clickOrderHistory() {
        wait.until(ExpectedConditions.elementToBeClickable(orderHistoryLink));
        driver.findElement(orderHistoryLink).click();}
    public void clickNewsletter() {
        wait.until(ExpectedConditions.elementToBeClickable(newsletterLink));
        driver.findElement(newsletterLink).click();}
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        driver.findElement(logoutLink).click();}
    //                  ===== ASSERTION  =====
    public void Assertion_login_invalidemail () {
        wait.until(ExpectedConditions.presenceOfElementLocated(pageTitle));
        Assert.assertTrue(driver.findElement(pageTitle).isDisplayed());
        driver.findElement(myAccountMenu).click();
        Assert.assertTrue(driver.findElement(myaccbtn).isDisplayed());}

}
