package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CategoryPage {

    WebDriver driver;
    WebDriverWait wait;
    By categoryHeading = By.cssSelector("#content h1, #content h2, #content h3");
    By sortDropdown = By.id("input-sort");
    By productNames = By.cssSelector(".product-thumb h4 a");
    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCategoryDisplayed(String expectedTitle) {
        try {
            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryHeading));
            return heading.getText().trim().equalsIgnoreCase(expectedTitle.trim());
        } catch (Exception e) {
            return false;
        }
    }

    public void selectSortOption(String optionText) {
        wait.until(ExpectedConditions.presenceOfElementLocated(sortDropdown));
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(optionText);
        // allow page to refresh
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getSelectedSortOption() {
        try {
            Select select = new Select(driver.findElement(sortDropdown));
            return select.getFirstSelectedOption().getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getFirstProductName() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productNames));
            List<WebElement> products = driver.findElements(productNames);
            if (!products.isEmpty()) {
                return products.get(0).getText();
            }
        } catch (Exception ignored) {
        }
        return "";
    }
}



