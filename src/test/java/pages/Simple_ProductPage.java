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

public class Simple_ProductPage {
    WebDriver driver;
    WebDriverWait wait;
    //                  ===== LOCATORS =====
    By productName = By.xpath("//*[@id=\"content\"]/div[1]/div[2]/h1");
    By productPrice = By.className("price-new");
    By addToCartButton = By.id("button-cart");
    By quantityInput = By.id("input-quantity");
    By addToWishlistButton = By.xpath("//button[@aria-label=\"Add to Wish List\"]");
    By productDescription = By.xpath("//*[@id=\"content\"]/ul/li[1]/a");
    By prod_descs_form = By.id("tab-description");
    By prod_specs = By.partialLinkText("//a[@href=\"#tab-specification\"]");
    By prod_specs_form = By.xpath("//*[@id=\"tab-specification\"]");
    By prod_review = By.xpath("//*[@id=\"content\"]/ul/li[3]/a");
    By prod_review_form = By.id("tab-review");
    By AlertMessage = By.xpath("//*[@id=\"alert\"]");
    By cartButton = By.xpath("//button[@class=\"btn btn-lg btn-dark d-block dropdown-toggle\"]");
    By totalprice_incart = By.xpath("//*[@id=\"cart\"]/div/ul/li/div/table/tbody/tr[4]/td[2]");
    By qnt_adddedtocart = By.xpath("//td[@class=\"text-end text-nowrap\"]");

    //                  ===== Constructor ======
    public Simple_ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //                  ===== ACTIONS =====
    public void clickCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        driver.findElement(cartButton).click();}
    public void setQuantity(String quantity) {
        wait.until(ExpectedConditions.presenceOfElementLocated(quantityInput));
        driver.findElement(quantityInput).clear();;
        driver.findElement(quantityInput).sendKeys((quantity));}
    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();}
    public void addToCartWithQuantity(String quantity) {
        wait.until(ExpectedConditions.presenceOfElementLocated(quantityInput));
        setQuantity(quantity);
        addToCart();}
    public void opencartdrop(){
        driver.findElement(cartButton).click();}
    public void addToWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(addToWishlistButton));
        driver.findElement(addToWishlistButton).click();}
    public void open_prod_descs(){
        wait.until(ExpectedConditions.elementToBeClickable(productDescription));
        driver.findElement(productDescription).click();}
    public void open_prod_specs(){
        wait.until(ExpectedConditions.elementToBeClickable(prod_specs));
        driver.findElement(prod_specs).click();}
    public void open_prod_reviews(){
        wait.until(ExpectedConditions.elementToBeClickable(prod_review));
        driver.findElement(prod_review).click();}
    //                  ===== ASSERTION  =====
    public void Assertion_open_simprod_page(){
        wait.until(ExpectedConditions.elementToBeClickable(productName));
        Assert.assertTrue(driver.findElement(productName).isDisplayed());}
    public void Assertion_open_simprod_desc(){
        wait.until(ExpectedConditions.elementToBeClickable(prod_descs_form));
        Assert.assertTrue(driver.findElement(prod_descs_form).getAttribute("class").contains("active"));}
    public void Assertion_open_simprod_specs(){
        wait.until(ExpectedConditions.elementToBeClickable(prod_specs_form));
        Assert.assertTrue(driver.findElement(prod_specs_form).getAttribute("class").contains("active"));
    }
    public void Assertion_open_simprod_revs(){
        wait.until(ExpectedConditions.elementToBeClickable(prod_review_form));
        Assert.assertTrue(driver.findElement(prod_review_form).getAttribute("class").contains("active"));}
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



