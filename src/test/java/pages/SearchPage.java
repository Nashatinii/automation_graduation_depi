package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    // ===== LOCATORS =====
    By searchResults = By.xpath("//div[contains(@class, 'product-thumb')]");
    By productName = By.xpath(".//h4/a");
    By productPrice = By.xpath(".//span[contains(@class, 'price-new')] | .//span[contains(@class, 'price')]");
    By addToCartButton = By.xpath(".//button[contains(@onclick, 'cart.add')]");
    By addToWishlistButton = By.xpath(".//button[contains(@onclick, 'wishlist.add')]");
    By noResultsMessage = By.xpath("//p[contains(text(), 'No products')]");
    By productImage = By.xpath(".//img");

    // Constructor
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== ACTIONS =====
    public int getSearchResultsCount() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(searchResults));
            return driver.findElements(searchResults).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<WebElement> getSearchResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchResults));
        return driver.findElements(searchResults);
    }

    public void clickProductByName(String productName) {
        By productLink = By.xpath("//h4/a[contains(text(), '" + productName + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(productLink));
        driver.findElement(productLink).click();
    }

    public void addFirstProductToCart() {
        List<WebElement> results = getSearchResults();
        if (!results.isEmpty()) {
            WebElement firstProduct = results.get(0);
            WebElement addToCart = firstProduct.findElement(addToCartButton);
            wait.until(ExpectedConditions.elementToBeClickable(addToCart));
            addToCart.click();
        }
    }

    public void addProductToCartByIndex(int index) {
        List<WebElement> results = getSearchResults();
        if (index < results.size()) {
            WebElement product = results.get(index);
            WebElement addToCart = product.findElement(addToCartButton);
            wait.until(ExpectedConditions.elementToBeClickable(addToCart));
            addToCart.click();
        }
    }

    public void addFirstProductToWishlist() {
        List<WebElement> results = getSearchResults();
        if (!results.isEmpty()) {
            WebElement firstProduct = results.get(0);
            WebElement addToWishlist = firstProduct.findElement(addToWishlistButton);
            wait.until(ExpectedConditions.elementToBeClickable(addToWishlist));
            addToWishlist.click();
        }
    }

    public String getProductName(int index) {
        List<WebElement> results = getSearchResults();
        if (index < results.size()) {
            return results.get(index).findElement(productName).getText();
        }
        return "";
    }

    public String getProductPrice(int index) {
        List<WebElement> results = getSearchResults();
        if (index < results.size()) {
            return results.get(index).findElement(productPrice).getText();
        }
        return "";
    }

    public boolean hasNoResults() {
        try {
            return driver.findElement(noResultsMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}




