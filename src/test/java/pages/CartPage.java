package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    //                  ===== LOCATORS =====
    By cartDropdownButton = By.xpath("//button[@class=\"btn btn-lg btn-dark d-block dropdown-toggle\"]");
    By shoppingCartLink = By.xpath("//a[@title=\"Shopping Cart\"]");
    By cartPageTitle = By.xpath("//h1[contains(text(), 'Shopping Cart')]");
    By cartTableRows = By.xpath("//div[@id=\"content\"]//table//tbody//tr");
    By productNameCell = By.xpath(".//td[2]/a");
    By quantityInput = By.xpath(".//td[3]//input[contains(@name,'quantity')]");
    By updateButton = By.xpath(".//td[3]//button[contains(@formaction,'edit')]");
    By removeButton = By.xpath(".//td[3]//a[contains(@href,'checkout/cart.remove')]");
    By cartTotalCell = By.xpath("//div[@id=\"content\"]//table//tfoot//tr[last()]/td[2]");
    By emptyCartMessage = By.xpath("//div[@id=\"shopping-cart\"]/p");
    By AlertMessage = By.xpath("//*[@id=\"alert\"]");
    By couponAccordionButton = By.cssSelector("button[data-bs-target='#collapse-coupon']");
    By couponCollapse = By.id("collapse-coupon");
    By couponInput = By.id("input-coupon");
    By applyCouponButton = By.cssSelector("button[form='form-coupon'][formaction*='coupon.save']");
    By removeCouponButton = By.cssSelector("button[form='form-coupon'][formaction*='coupon.remove']");

    //                  ===== Constructor ======
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //                  ===== ACTIONS =====
    public void openCartDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(cartDropdownButton));
        driver.findElement(cartDropdownButton).click();}
    public void openCartPage() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink));
        driver.findElement(shoppingCartLink).click();}
    public void updateProductQuantity(String qnt) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartTableRows));
        List<WebElement> rows = driver.findElements(cartTableRows);
        WebElement firstRow = rows.get(0);
        WebElement qntInput = firstRow.findElement(quantityInput);
        qntInput.clear();
        qntInput.sendKeys(qnt);
        firstRow.findElement(updateButton).click();}
    public void removeProduct() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartTableRows));
        List<WebElement> rows = driver.findElements(cartTableRows);
        WebElement firstRow = rows.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(firstRow.findElement(removeButton)));
        firstRow.findElement(removeButton).click();}
    public void removeAllProducts() {
        int guard = 0;
        while (true) {
            List<WebElement> rows = driver.findElements(cartTableRows);
            if (rows.size()==0 || guard>10) {
                break;}
            WebElement firstRow = rows.get(0);
            WebElement removeBtn = firstRow.findElement(removeButton);
            try {
                wait.until(ExpectedConditions.elementToBeClickable(removeBtn));
                removeBtn.click();
                wait.until(ExpectedConditions.stalenessOf(firstRow));
            } catch (StaleElementReferenceException e) {
            }
            guard++;}
    }
    public String getCartTotal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartTotalCell));
        return driver.findElement(cartTotalCell).getText();}
    public void openCouponBlock() {
        wait.until(ExpectedConditions.presenceOfElementLocated(couponAccordionButton));
        WebElement couponBtn = driver.findElement(couponAccordionButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",couponBtn);
        wait.until(ExpectedConditions.elementToBeClickable(couponBtn));
        try {
            couponBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",couponBtn);
        }
        wait.until(ExpectedConditions.attributeContains(couponCollapse,"class","show"));}
    public void applyCoupon(String code) {
        openCouponBlock();
        wait.until(ExpectedConditions.visibilityOfElementLocated(couponInput));
        driver.findElement(couponInput).clear();
        driver.findElement(couponInput).sendKeys(code);
        wait.until(ExpectedConditions.elementToBeClickable(applyCouponButton));
        driver.findElement(applyCouponButton).click();}
    public void removeCoupon() {
        wait.until(ExpectedConditions.elementToBeClickable(removeCouponButton));
        driver.findElement(removeCouponButton).click();}

    //                  ===== ASSERTION  =====
    public void Assertion_product_exists_in_cart() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartTableRows));
        List<WebElement> rows = driver.findElements(cartTableRows);
        Assert.assertTrue(rows.size()>0);}
    public void Assertion_quantity_updated(String expectedQnt) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartTableRows));
        List<WebElement> rows = driver.findElements(cartTableRows);
        WebElement firstRow = rows.get(0);
        String actualQnt = firstRow.findElement(quantityInput).getAttribute("value");
        Assert.assertEquals(actualQnt,expectedQnt);}
    public void Assertion_product_removed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
        Assert.assertTrue(driver.findElement(emptyCartMessage).getText().contains("Your shopping cart is empty"));}
    public void Assertion_cart_total_correct(float expected) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartTotalCell));
        String cart_total = driver.findElement(cartTotalCell).getText().replaceAll("[$,,]","");
        float cartTotal = Float.parseFloat(cart_total);
        Assert.assertEquals(cartTotal,expected);}
    public void Assertion_valid_coupon_applied() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AlertMessage));
        Assert.assertTrue(driver.findElement(AlertMessage).getText().toLowerCase().contains("success"));}
    public void Assertion_invalid_coupon_message() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AlertMessage));
        Assert.assertTrue(driver.findElement(AlertMessage).getText().toLowerCase().contains("warning")
                ||driver.findElement(AlertMessage).getText().toLowerCase().contains("invalid"));}
}
