package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class logoutsuccesspage {
    WebDriver driver;
    WebDriverWait wait;
    //                  ===== LOCATORS =====
    By logoutmessage = By.xpath("//*[@id=\"content\"]/h1");
    By continueBtn = By.linkText("Continue");
    By myAccountMenu = By.xpath("//i[@class=\"fa-solid fa-user\"]");
    By loginbtn = By.xpath("//*[@id=\"top\"]/div/div/div[2]/ul/li[2]/div/ul/li[2]/a");
    //                  ===== Constructor ======
    public logoutsuccesspage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));}
    //                  ===== ACTIONS =====

    //                  ===== ASSERTION  =====
    public void Assertion_logout () {
        wait.until(ExpectedConditions.presenceOfElementLocated(continueBtn));
        Assert.assertEquals(driver.getCurrentUrl(),"http://localhost/opencartproject/index.php?route=account/logout&language=en-gb");
        Assert.assertEquals(driver.findElement(logoutmessage).getText(),"Account Logout");
        driver.findElement(myAccountMenu).click();
        Assert.assertEquals(driver.findElement(loginbtn).getText(),"Login");
    }
}
