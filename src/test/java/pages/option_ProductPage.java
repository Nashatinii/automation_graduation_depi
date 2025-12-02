package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class option_ProductPage {
    WebDriver driver;
    WebDriverWait wait;
    //                  ===== LOCATORS =====
    By productName = By.xpath("//*[@id=\"content\"]/div[1]/div[2]/h1");
    By productPrice = By.className("price-new");
    By prod_options = By.name("option[226]");
    By prod_selected_option = By.xpath("//option[@value=\"16\"]");
    By error_unselectedopt = By.xpath("//div[@class=\"invalid-feedback d-block\"]");
    By addToCartButton = By.id("button-cart");
    By quantityInput = By.id("input-quantity");
    By addToWishlistButton = By.xpath("//button[@aria-label=\"Add to Wish List\"]");
    By AlertMessage = By.xpath("//*[@id=\"alert\"]");
    By cartButton = By.xpath("//button[@class=\"btn btn-lg btn-dark d-block dropdown-toggle\"]");
    By totalprice_incart = By.xpath("//*[@id=\"cart\"]/div/ul/li/div/table/tbody/tr[4]/td[2]");
    By qnt_adddedtocart = By.xpath("//td[@class=\"text-end text-nowrap\"]");
    //                  ===== Constructor ======
    public option_ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //                  ===== ACTIONS =====
    public void set_option () {
        wait.until(ExpectedConditions.elementToBeClickable(prod_options));
        driver.findElement(prod_options).click();
        wait.until(ExpectedConditions.elementToBeClickable(prod_selected_option));
        driver.findElement(prod_selected_option).click();}
    public void setQuantity(String quantity) {
        wait.until(ExpectedConditions.presenceOfElementLocated(quantityInput));
        driver.findElement(quantityInput).clear();;
        driver.findElement(quantityInput).sendKeys((quantity));}
    public void addToCart_withno_opt() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();}
    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        set_option();
        driver.findElement(addToCartButton).click();}
    public void addToCartWithQuantity(String quantity) {
        setQuantity(quantity);
        addToCart();}
    public void opencartdrop(){
        driver.findElement(cartButton).click();}
    public void addToWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(addToWishlistButton));
        driver.findElement(addToWishlistButton).click();}
    //                  ===== ASSERTION  =====
    public void Assertion_open_optrod_page(){
        wait.until(ExpectedConditions.elementToBeClickable(productName));
        Assert.assertTrue(driver.findElement(productName).isDisplayed());}
    public void Assertion_addtocart_withnoopt (){
        Assert.assertEquals(driver.findElement(error_unselectedopt).getText(),"Select required!");
    }
    public void Assertion_addtocart_withoutqnt () {
        wait.until(ExpectedConditions.elementToBeClickable(AlertMessage));
        Assert.assertTrue(driver.findElement(AlertMessage).getText().contains("Success: You have added")
                &&driver.findElement(AlertMessage).getText().contains("shopping cart!"));
        opencartdrop();
        Assert.assertEquals(driver.findElement(totalprice_incart).getText(),driver.findElement(productPrice).getText());
        Assert.assertEquals(driver.findElement(qnt_adddedtocart).getText(),"x 1");}
    public void Assertion_addtocart_withqnt (String qnt) {
        wait.until(ExpectedConditions.elementToBeClickable(AlertMessage));
        Assert.assertTrue(driver.findElement(AlertMessage).getText().contains("Success: You have added")
                &&driver.findElement(AlertMessage).getText().contains("shopping cart!"));
        opencartdrop();
        String unit_price= driver.findElement(productPrice).getText().replaceAll("[$,,]","");
        String cart_price = driver.findElement(totalprice_incart).getText().replaceAll("[$,,]","");
        float total_price= Float.parseFloat(unit_price)*Integer.parseInt(qnt);
        float cart_total = Float.parseFloat(cart_price);
        Assert.assertEquals(cart_total,total_price);
        Assert.assertEquals(driver.findElement(qnt_adddedtocart).getText(),"x "+qnt);}
    public void Assertion_addprod_towishlist(){
        wait.until(ExpectedConditions.elementToBeClickable(AlertMessage));
        Assert.assertTrue(driver.findElement(AlertMessage).getText().contains("Success: You have added")
                &&driver.findElement(AlertMessage).getText().contains("wish list!"));
    }

}


