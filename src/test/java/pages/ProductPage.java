package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    // ===== LOCATORS =====
    By productName = By.xpath("//h1");
    By productPrice = By.xpath("//ul[@class='list-unstyled']//li//h2");
    By addToCartButton = By.id("button-cart");
    By quantityInput = By.id("input-quantity");
    By addToWishlistButton = By.xpath("//button[contains(@onclick, 'wishlist.add')]");
    By productDescription = By.id("tab-description");
    By successMessage = By.xpath("//div[contains(@class, 'alert-success')]");
    By optionSelect = By.xpath("//select[@id='input-option'] | //select[contains(@id, 'input-option')]");
    By optionSelects = By.cssSelector("select[id^='input-option']");
    By optionRadios = By.cssSelector("input[type='radio'][name^='option']");
    By optionCheckboxes = By.cssSelector("input[type='checkbox'][name^='option']");
    By optionTextInputs = By.cssSelector("input[type='text'][id^='input-option']");
    By optionDateInputs = By.cssSelector("input[type='date'], input[data-date-format][id^='input-option']");
    By optionTextareaInputs = By.cssSelector("textarea[id^='input-option']");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== ACTIONS =====
    public String getProductName() {
        wait.until(ExpectedConditions.presenceOfElementLocated(productName));
        return driver.findElement(productName).getText();
    }

    public String getProductPrice() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(productPrice));
            return driver.findElement(productPrice).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void setQuantity(int quantity) {
        wait.until(ExpectedConditions.presenceOfElementLocated(quantityInput));
        WebElement qtyInput = driver.findElement(quantityInput);
        qtyInput.clear();
        qtyInput.sendKeys(String.valueOf(quantity));
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();
    }

    public void addToCartWithQuantity(int quantity) {
        setQuantity(quantity);
        addToCart();
    }

    public void fillAllRequiredOptionsIfPresent() {
        try {
            List<WebElement> selects = driver.findElements(optionSelects);
            for (WebElement selectElement : selects) {
                Select select = new Select(selectElement);
                for (WebElement option : select.getOptions()) {
                    String value = option.getAttribute("value");
                    if (value != null && !value.isBlank()) {
                        select.selectByValue(value);
                        break;
                    }
                }
            }
        } catch (Exception ignored) {
        }

        try {
            List<WebElement> radios = driver.findElements(optionRadios);
            Set<String> handled = new HashSet<>();
            for (WebElement radio : radios) {
                String name = radio.getAttribute("name");
                if (!handled.contains(name)) {
                    handled.add(name);
                    if (!radio.isSelected()) {
                        try {
                            radio.click();
                        } catch (Exception e) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }

        try {
            List<WebElement> checkboxes = driver.findElements(optionCheckboxes);
            Set<String> checked = new HashSet<>();
            for (WebElement checkbox : checkboxes) {
                String name = checkbox.getAttribute("name");
                if (!checked.contains(name)) {
                    checked.add(name);
                    if (!checkbox.isSelected()) {
                        try {
                            checkbox.click();
                        } catch (Exception e) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }

        try {
            List<WebElement> textInputs = driver.findElements(optionTextInputs);
            for (WebElement input : textInputs) {
                input.clear();
                input.sendKeys("Automated Option");
            }
        } catch (Exception ignored) {
        }

        try {
            List<WebElement> dateInputs = driver.findElements(optionDateInputs);
            String dateValue = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            for (WebElement input : dateInputs) {
                input.clear();
                input.sendKeys(dateValue);
            }
        } catch (Exception ignored) {
        }

        try {
            List<WebElement> textareas = driver.findElements(optionTextareaInputs);
            for (WebElement textarea : textareas) {
                textarea.clear();
                textarea.sendKeys("Automated description");
            }
        } catch (Exception ignored) {
        }
    }

    public void addToCartWithDefaultOptions() {
        fillAllRequiredOptionsIfPresent();
        addToCart();
    }

    public void addToWishlist() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addToWishlistButton));
            driver.findElement(addToWishlistButton).click();
        } catch (Exception e) {
            // Wishlist button might not be available
        }
    }

    public void selectOption(String optionValue) {
        try {
            WebElement selectElement = driver.findElement(optionSelect);
            Select select = new Select(selectElement);
            select.selectByVisibleText(optionValue);
        } catch (Exception e) {
            // Option select might not be available for all products
        }
    }

    public boolean isAddToCartSuccessful() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessage() {
        try {
            return driver.findElement(successMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }
}


