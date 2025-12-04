package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    //                  ===== LOCATORS =====
    By myAccountMenu = By.xpath("//i[@class=\"fa-solid fa-user\"]");
    By registerLink = By.linkText("Register");
    By loginLink = By.linkText("Login");
    By searchInput = By.name("search");
    By searchButton = By.xpath("//i[@class=\"fa-solid fa-magnifying-glass\"]");
    By shoppingCart = By.xpath("//a[@title=\"Shopping Cart\"]");
    By wishlistLink = By.id("wishlist-total");
    By checkoutLink = By.xpath("//a[@title=\"Checkout\"]");
    By cartDropdownButton = By.xpath("//button[@class=\"btn btn-lg btn-dark d-block dropdown-toggle\"]");
    By removefrmsCartdrop = By.xpath("//*[@id=\"cart\"]/div/ul/li/table/tbody/tr[1]/td[5]/form/button");
    By clicksimpleprod  = By.xpath("//img[@src=\"http://localhost/opencartproject/image/cache/catalog/demo/iphone_1-200x200.jpg\"]");
    By clickprodwithopt = By.xpath("//img[@src=\"http://localhost/opencartproject/image/cache/catalog/demo/canon_eos_5d_1-200x200.jpg\"]");
    By viewcartfrmcartdrop = By.linkText("View Cart");
    By checkoutfrmcartdrop = By.xpath("//*[@id=\"cart\"]/div/ul/li/div/p/a[2]/strong");
    By categ_select = By.xpath("//*[@id=\"navbar-menu\"]/ul/li[1]");
    By open_categ = By.xpath("//*[@id=\"navbar-menu\"]/ul/li[1]/div/ul/li[2]");
    By open_subcateg = By.xpath("//*[@id=\"navbar-menu\"]/ul/li[1]/div/div/ul/li[2]");
    //                  ===== Constructor ======
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));}
    //                  ===== ACTIONS =====
    public void openRegisterPage() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountMenu));
        driver.findElement(myAccountMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        driver.findElement(registerLink).click();}
    public void openLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountMenu));
        driver.findElement(myAccountMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        driver.findElement(loginLink).click();}
    public void open_simpleprod_Page() {
        wait.until(ExpectedConditions.elementToBeClickable(clicksimpleprod));
        driver.findElement(clicksimpleprod).click();}
    public void open_optprod_page(){
        wait.until(ExpectedConditions.elementToBeClickable(clickprodwithopt));
        driver.findElement(clickprodwithopt).click();}
    public void searchForProduct(String productName) {
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();}
    public void openShoppingCart() {
        driver.findElement(shoppingCart).click();}
    public void openWishlist() {
        driver.findElement(wishlistLink).click();}
    public void openCheckout() {
        driver.findElement(checkoutLink).click();}
    public void Remove_frmsCartdrop() {
        driver.findElement(removefrmsCartdrop).click();}
    public void viewcart_frmCartdrop() {
        driver.findElement(viewcartfrmcartdrop).click();}
    public void Checkout_frmCartdrop() {
        wait.until(ExpectedConditions.elementToBeClickable(cartDropdownButton));
        driver.findElement(cartDropdownButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutfrmcartdrop));
        driver.findElement(checkoutfrmcartdrop).click();}
    public void open_categ_page(){
        driver.findElement(categ_select).click();
        driver.findElement(open_categ).click();}
    public void open_subcateg_page(){
        driver.findElement(categ_select).click();
        driver.findElement(open_subcateg).click();}
    //                  ===== ASSERTION  =====

}
