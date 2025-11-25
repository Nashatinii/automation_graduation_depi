package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    // ===== LOCATORS =====
    By myAccountMenu = By.xpath("//a[contains(@class, 'dropdown-toggle') and .//i[contains(@class,'fa-user')]]");
    By registerLink = By.cssSelector("a.dropdown-item[href*='route=account/register']");
    By loginLink = By.cssSelector("a.dropdown-item[href*='route=account/login']");
    By searchInput = By.name("search");
    By searchButton = By.xpath("//button[@type='submit' and .//i[contains(@class,'fa-magnifying-glass')]]");
    By shoppingCart = By.xpath("//a[contains(@href, 'route=checkout/cart')]");
    By wishlistLink = By.xpath("//a[contains(@href, 'route=account/wishlist')]");
    By checkoutLink = By.xpath("//a[contains(@href, 'route=checkout/checkout')]");
    By cartButton = By.xpath("//button[contains(@class, 'btn-dark') and .//i[contains(@class,'fa-cart-shopping')]]");
    By cartItemsCount = By.xpath("//button[contains(@class, 'btn-dark')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== NAVIGATION METHODS =====
    public void openRegisterPage() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountMenu));
        driver.findElement(myAccountMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        driver.findElement(registerLink).click();
    }

    public void openLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountMenu));
        driver.findElement(myAccountMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        driver.findElement(loginLink).click();
    }

    public void searchForProduct(String productName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void openShoppingCart() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(shoppingCart));
            WebElement cartElement = driver.findElement(shoppingCart);
            
            // Scroll element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartElement);
            
            // Wait a bit for scroll to complete
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Try regular click first
            try {
                wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
                cartElement.click();
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                // If intercepted, use JavaScript click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartElement);
            }
        } catch (Exception e) {
            // Fallback: navigate directly to cart URL
            driver.get("http://localhost/opencartproject/index.php?route=checkout/cart&language=en-gb");
        }
    }

    public void openWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(wishlistLink));
        driver.findElement(wishlistLink).click();
    }

    public void openCheckout() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(checkoutLink));
            WebElement checkoutElement = driver.findElement(checkoutLink);
            
            // Scroll element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutElement);
            
            // Wait a bit for scroll to complete
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Try regular click first
            try {
                wait.until(ExpectedConditions.elementToBeClickable(checkoutLink));
                checkoutElement.click();
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                // If intercepted, use JavaScript click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutElement);
            }
        } catch (Exception e) {
            // Fallback: navigate directly to checkout URL
            driver.get("http://localhost/opencartproject/index.php?route=checkout/checkout&language=en-gb");
        }
    }

    public String getCartItemsCount() {
        try {
            WebElement cartBtn = driver.findElement(cartButton);
            String text = cartBtn.getText();
            // Extract number from text like "0 item(s) - 0.00€"
            if (text.contains("item")) {
                return text.split(" ")[0];
            }
            return "0";
        } catch (Exception e) {
            return "0";
        }
    }

    public void clickCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        driver.findElement(cartButton).click();
    }
}
