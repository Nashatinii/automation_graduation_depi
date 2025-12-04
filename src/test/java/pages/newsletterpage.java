package pages;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class newsletterpage {
    WebDriver driver;

    WebDriverWait wait;
    //                  ===== LOCATORS =====
    By subscribecheckbox = By.id("input-newsletter");
    By continueBtn = By.linkText("Continue");
    By backbtn = By.linkText("Back");
    //                  ===== Constructor ======
    public newsletterpage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
    //                  ===== ACTIONS =====
    public boolean check_for_subscrippton(){
        WebElement subscribeswich = driver.findElement(subscribecheckbox);
        boolean isCheckedBefore = subscribeswich.isSelected();
        return isCheckedBefore;
        }
    public void click_con_btn (){
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        driver.findElement(continueBtn).click();}
    public void click_back_btn (){
        wait.until(ExpectedConditions.elementToBeClickable(backbtn));
        driver.findElement(backbtn).click();}
    //                  ===== ASSERTION  =====
    public void Assert_user_subs_newslettter(){
        wait.until(ExpectedConditions.elementToBeClickable(subscribecheckbox));
        Assert.assertTrue(check_for_subscrippton());}
    public void Assert_user_notsubs_newslettter(){
        Assert.assertFalse(check_for_subscrippton());}
}
