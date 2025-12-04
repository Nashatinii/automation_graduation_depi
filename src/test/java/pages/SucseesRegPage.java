package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SucseesRegPage {
    WebDriver driver;
    WebDriverWait wait;
    //                  ===== LOCATORS =====
    By successRegmessage = By.linkText("Your Account Has Been Created!");
    By continueBtn = By.linkText("Continue");
    By myAccountMenu = By.xpath("//i[@class=\"fa-solid fa-user\"]");
    By myaccbtn = By.xpath("//*[@id=\"top\"]/div/div/div[2]/ul/li[2]/div/ul/li[1]/a");

    //                  ===== Constructor ======
    public SucseesRegPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));}
    //                  ===== ACTIONS =====
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        driver.findElement(continueBtn).click();}
    public void clickmyacc() {
        driver.findElement(myAccountMenu).click();
        driver.findElement(myaccbtn).click();}
    //                  ===== ASSERTION  =====
    public void Assert_user_reg_succesful  (){
        Assert.assertTrue(driver.findElement(successRegmessage).isDisplayed());
        driver.findElement(myAccountMenu).click();
        Assert.assertTrue(driver.findElement(myaccbtn).isDisplayed());}
}
