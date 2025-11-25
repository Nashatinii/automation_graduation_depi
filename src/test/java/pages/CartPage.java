package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    // ===== LOCATORS =====
    By pageTitle = By.xpath("//h1[contains(text(), 'Shopping Cart')]");
    By cartTable = By.xpath("//table[@class='table table-bordered']");
    By productRows = By.xpath("//table[@class='table table-bordered']//tbody//tr");
    By productImage = By.xpath(".//td[1]//img");
    By productName = By.xpath(".//td[2]//a");
    By productModel = By.xpath(".//td[2]//small");
    By productQuantity = By.xpath(".//td[3]//input[@name='quantity']");
    By updateButton = By.xpath(".//td[3]//button[@type='submit' and contains(@class, 'btn-primary')]");
    By removeLink = By.xpath(".//td[3]//a[contains(@class, 'btn-danger')]");
    By productUnitPrice = By.xpath(".//td[4]");
    By productTotal = By.xpath(".//td[5]");
    By continueShoppingLink = By.xpath("//a[contains(@href, 'route=common/home') and contains(@class, 'btn-light')]");
    By checkoutButton = By.partialLinkText("Checkout");
    By subtotal = By.xpath("//tfoot[@id='checkout-total']//tr[contains(., 'Sub-Total')]//td[2]");
    By ecoTax = By.xpath("//tfoot[@id='checkout-total']//tr[contains(., 'Eco Tax')]//td[2]");
    By vat = By.xpath("//tfoot[@id='checkout-total']//tr[contains(., 'VAT')]//td[2]");
    By total = By.xpath("//tfoot[@id='checkout-total']//tr[contains(., 'Total')]//td[2]");
    By emptyCartMessage = By.xpath("//p[contains(text(), 'Your shopping cart is empty')]");
    By couponAccordionButton = By.cssSelector("button[data-bs-target='#collapse-coupon']");
    By couponCollapse = By.id("collapse-coupon");
    By couponInput = By.id("input-coupon");
    By applyCouponButton = By.cssSelector("button[form='form-coupon'][formaction*='coupon.save']");
    By removeCouponButton = By.cssSelector("button[form='form-coupon'][formaction*='coupon.remove']");
    By alertMessages = By.cssSelector("#alert .alert");
    By alertSuccess = By.cssSelector("#alert .alert-success");
    By alertDanger = By.cssSelector("#alert .alert-danger");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== VALIDATION METHODS =====
    public boolean isCartPageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(pageTitle));
            return driver.findElement(pageTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCartEmpty() {
        try {
            return driver.findElement(emptyCartMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getCartItemsCount() {
        try {
            List<WebElement> rows = driver.findElements(productRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // ===== ACTIONS =====
    public void clickViewCart() {
        // This is for the "View Cart" link in the cart dropdown
        By viewCartLink = By.xpath("//a[contains(@href, 'route=checkout/cart') and contains(., 'View Cart')]");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(viewCartLink));
            driver.findElement(viewCartLink).click();
        } catch (Exception e) {
            // Try alternative locator
            By altViewCart = By.xpath("//a[contains(@href, 'checkout/cart')]");
            wait.until(ExpectedConditions.elementToBeClickable(altViewCart));
            driver.findElement(altViewCart).click();
        }
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        driver.findElement(checkoutButton).click();
    }

    public void updateQuantity(int rowIndex, int quantity) {
        List<WebElement> rows = driver.findElements(productRows);
        if (rowIndex < rows.size()) {
            WebElement quantityInput = rows.get(rowIndex).findElement(productQuantity);
            quantityInput.clear();
            quantityInput.sendKeys(String.valueOf(quantity));
        }
    }

    public void removeProduct(int rowIndex) {
        List<WebElement> rows = driver.findElements(productRows);
        if (rowIndex < rows.size()) {
            WebElement removeLinkElement = rows.get(rowIndex).findElement(removeLink);
            wait.until(ExpectedConditions.elementToBeClickable(removeLinkElement));
            removeLinkElement.click();
            
            // Wait for removal to process
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void clickUpdate(int rowIndex) {
        List<WebElement> rows = driver.findElements(productRows);
        if (rowIndex < rows.size()) {
            try {
                WebElement updateBtn = rows.get(rowIndex).findElement(updateButton);
                wait.until(ExpectedConditions.elementToBeClickable(updateBtn));
                updateBtn.click();
                
                // Wait for update to process
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } catch (Exception e) {
                // Update might not always be present
            }
        }
    }

    public void continueShopping() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueShoppingLink));
            driver.findElement(continueShoppingLink).click();
        } catch (Exception e) {
            // Continue shopping link might not always be present
        }
    }
    
    private void openCouponSection() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(couponAccordionButton));
            WebElement collapse = driver.findElement(couponCollapse);
            if (!collapse.getAttribute("class").contains("show")) {
                driver.findElement(couponAccordionButton).click();
                wait.until(ExpectedConditions.attributeContains(couponCollapse, "class", "show"));
            }
        } catch (Exception e) {
            // ignore
        }
    }
    
    public String applyCouponCode(String couponCode) {
        openCouponSection();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(couponInput));
            WebElement input = driver.findElement(couponInput);
            input.clear();
            input.sendKeys(couponCode);
            
            WebElement applyButton = driver.findElement(applyCouponButton);
            wait.until(ExpectedConditions.elementToBeClickable(applyButton));
            applyButton.click();
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessages));
            return driver.findElement(alertMessages).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String removeCouponCode() {
        openCouponSection();
        try {
            WebElement removeButton = driver.findElement(removeCouponButton);
            wait.until(ExpectedConditions.elementToBeClickable(removeButton));
            removeButton.click();
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessages));
            return driver.findElement(alertMessages).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getLatestAlertMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessages));
            return driver.findElement(alertMessages).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isCouponSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(alertSuccess));
            return driver.findElement(alertSuccess).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isCouponErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(alertDanger));
            return driver.findElement(alertDanger).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ===== GETTER METHODS =====
    public String getProductName(int rowIndex) {
        List<WebElement> rows = driver.findElements(productRows);
        if (rowIndex < rows.size()) {
            return rows.get(rowIndex).findElement(productName).getText();
        }
        return "";
    }

    public String getProductQuantity(int rowIndex) {
        List<WebElement> rows = driver.findElements(productRows);
        if (rowIndex < rows.size()) {
            return rows.get(rowIndex).findElement(productQuantity).getAttribute("value");
        }
        return "";
    }

    public String getProductUnitPrice(int rowIndex) {
        List<WebElement> rows = driver.findElements(productRows);
        if (rowIndex < rows.size()) {
            return rows.get(rowIndex).findElement(productUnitPrice).getText();
        }
        return "";
    }
    
    public String getProductModel(int rowIndex) {
        List<WebElement> rows = driver.findElements(productRows);
        if (rowIndex < rows.size()) {
            try {
                return rows.get(rowIndex).findElement(productModel).getText();
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }

    public String getProductTotal(int rowIndex) {
        List<WebElement> rows = driver.findElements(productRows);
        if (rowIndex < rows.size()) {
            return rows.get(rowIndex).findElement(productTotal).getText();
        }
        return "";
    }

    public String getSubtotal() {
        try {
            return driver.findElement(subtotal).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getTotal() {
        try {
            return driver.findElement(total).getText();
        } catch (Exception e) {
            return "";
        }
    }
}

