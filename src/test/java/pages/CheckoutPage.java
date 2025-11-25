package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    // ===== LOCATORS =====
    By pageTitle = By.xpath("//h1[contains(text(), 'Checkout')]");
    
    // Shipping Address
    By existingAddressRadio = By.id("input-shipping-existing");
    By newAddressRadio = By.id("input-shipping-new");
    By shippingAddressSelect = By.id("input-shipping-address");
    By shippingFirstName = By.id("input-shipping-firstname");
    By shippingLastName = By.id("input-shipping-lastname");
    By shippingCompany = By.id("input-shipping-company");
    By shippingAddress1 = By.id("input-shipping-address-1");
    By shippingAddress2 = By.id("input-shipping-address-2");
    By shippingCity = By.id("input-shipping-city");
    By shippingPostcode = By.id("input-shipping-postcode");
    By shippingCountry = By.id("input-shipping-country");
    By shippingZone = By.id("input-shipping-zone");
    By buttonShippingAddress = By.id("button-shipping-address");
    
    // Shipping Method
    By shippingMethodInput = By.id("input-shipping-method");
    By buttonShippingMethods = By.id("button-shipping-methods");
    By modalShipping = By.id("modal-shipping");
    By shippingMethodRadio = By.xpath("//div[@id='modal-shipping']//input[@name='shipping_method']");
    By buttonShippingMethod = By.id("button-shipping-method");
    By freeShippingRadio = By.id("input-shipping-method-free-free");
    By flatRateShippingRadio = By.id("input-shipping-method-flat-flat");
    
    // Payment Method
    By paymentMethodInput = By.id("input-payment-method");
    By buttonPaymentMethods = By.id("button-payment-methods");
    By modalPayment = By.id("modal-payment");
    By paymentMethodRadio = By.xpath("//div[@id='modal-payment']//input[@name='payment_method']");
    By buttonPaymentMethod = By.id("button-payment-method");
    By agreeTermsCheckbox = By.id("input-checkout-agree");
    By bankTransferRadio = By.id("input-payment-method-bank_transfer-bank-transfer");
    By cashOnDeliveryRadio = By.id("input-payment-method-cod-cod");
    By chequeRadio = By.id("input-payment-method-cheque-cheque");
    By commentTextarea = By.id("input-comment");
    
    // Order Confirmation
    By confirmOrderButton = By.xpath("//button[contains(@class, 'btn-primary') and contains(text(), 'Confirm Order')]");
    By orderSummaryTable = By.xpath("//div[@id='checkout-confirm']//table[@class='table table-bordered table-hover']");
    By orderTotal = By.xpath("//div[@id='checkout-confirm']//tfoot//tr[contains(., 'Total')]//td[2]");
    By checkoutConfirmSection = By.id("checkout-confirm");
    By orderSuccessHeading = By.xpath("//h1[contains(text(), 'Your order has been placed')]");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== VALIDATION METHODS =====
    public boolean isCheckoutPageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(pageTitle));
            return driver.findElement(pageTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ===== SHIPPING ADDRESS METHODS =====
    public void selectExistingAddress(String addressValue) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(existingAddressRadio));
            driver.findElement(existingAddressRadio).click();
            
            wait.until(ExpectedConditions.presenceOfElementLocated(shippingAddressSelect));
            Select addressSelect = new Select(driver.findElement(shippingAddressSelect));
            addressSelect.selectByValue(addressValue);
            
            // Wait for address selection to process (AJAX call)
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void selectFirstExistingAddress() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(existingAddressRadio));
            driver.findElement(existingAddressRadio).click();
            
            wait.until(ExpectedConditions.presenceOfElementLocated(shippingAddressSelect));
            Select addressSelect = new Select(driver.findElement(shippingAddressSelect));
            
            // Select first non-empty option
            List<WebElement> options = addressSelect.getOptions();
            for (WebElement option : options) {
                String value = option.getAttribute("value");
                if (value != null && !value.isEmpty()) {
                    addressSelect.selectByValue(value);
                    break;
                }
            }
            
            // Wait for address selection to process (AJAX call)
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectNewAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(newAddressRadio));
        driver.findElement(newAddressRadio).click();
    }

    public void fillNewShippingAddress(String firstName, String lastName, String company, 
                                       String address1, String city, String postcode, 
                                       String country, String zone) {
        selectNewAddress();
        
        driver.findElement(shippingFirstName).sendKeys(firstName);
        driver.findElement(shippingLastName).sendKeys(lastName);
        if (company != null && !company.isEmpty()) {
            driver.findElement(shippingCompany).sendKeys(company);
        }
        driver.findElement(shippingAddress1).sendKeys(address1);
        driver.findElement(shippingCity).sendKeys(city);
        driver.findElement(shippingPostcode).sendKeys(postcode);
        
        Select countrySelect = new Select(driver.findElement(shippingCountry));
        countrySelect.selectByVisibleText(country);
        
        // Wait for zones to load
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        Select zoneSelect = new Select(driver.findElement(shippingZone));
        zoneSelect.selectByVisibleText(zone);
    }

    public void clickContinueShippingAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonShippingAddress));
        driver.findElement(buttonShippingAddress).click();
        
        // Wait for next step to load (AJAX call)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ===== SHIPPING METHOD METHODS =====
    public void clickChooseShippingMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonShippingMethods));
        driver.findElement(buttonShippingMethods).click();
    }

    public void selectShippingMethod(String methodCode) {
        clickChooseShippingMethod();
        
        // Wait for modal to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(modalShipping));
        
        // Select the shipping method
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(shippingMethodRadio));
            List<WebElement> methods = driver.findElements(shippingMethodRadio);
            
            for (WebElement method : methods) {
                if (method.getAttribute("value").equals(methodCode)) {
                    // Scroll into view
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", method);
                    Thread.sleep(500);
                    method.click();
                    break;
                }
            }
            
            // Click continue
            wait.until(ExpectedConditions.elementToBeClickable(buttonShippingMethod));
            driver.findElement(buttonShippingMethod).click();
            
            // Wait for modal to close and page to update
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void selectFreeShipping() {
        selectShippingMethod("free.free");
    }
    
    public void selectFlatRateShipping() {
        selectShippingMethod("flat.flat");
    }

    public void selectFirstShippingMethod() {
        clickChooseShippingMethod();
        
        // Wait for modal to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(modalShipping));
        
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(shippingMethodRadio));
            List<WebElement> methods = driver.findElements(shippingMethodRadio);
            
            if (!methods.isEmpty()) {
                // First method is usually already checked, but click it to be sure
                WebElement firstMethod = methods.get(0);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstMethod);
                Thread.sleep(500);
                firstMethod.click();
                
                // Click continue
                wait.until(ExpectedConditions.elementToBeClickable(buttonShippingMethod));
                driver.findElement(buttonShippingMethod).click();
                
                // Wait for modal to close and page to update
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== PAYMENT METHOD METHODS =====
    public void clickChoosePaymentMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonPaymentMethods));
        driver.findElement(buttonPaymentMethods).click();
    }

    public void selectPaymentMethod(String methodCode) {
        clickChoosePaymentMethod();
        
        // Wait for modal to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(modalPayment));
        
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(paymentMethodRadio));
            List<WebElement> methods = driver.findElements(paymentMethodRadio);
            
            for (WebElement method : methods) {
                if (method.getAttribute("value").equals(methodCode)) {
                    // Scroll into view
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", method);
                    Thread.sleep(500);
                    method.click();
                    break;
                }
            }
            
            // Click continue
            wait.until(ExpectedConditions.elementToBeClickable(buttonPaymentMethod));
            driver.findElement(buttonPaymentMethod).click();
            
            // Wait for modal to close and page to update
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void selectBankTransfer() {
        selectPaymentMethod("bank_transfer.bank_transfer");
    }
    
    public void selectCashOnDelivery() {
        selectPaymentMethod("cod.cod");
    }
    
    public void selectCheque() {
        selectPaymentMethod("cheque.cheque");
    }

    public void selectFirstPaymentMethod() {
        clickChoosePaymentMethod();
        
        // Wait for modal to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(modalPayment));
        
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(paymentMethodRadio));
            List<WebElement> methods = driver.findElements(paymentMethodRadio);
            
            if (!methods.isEmpty()) {
                // First method is usually already checked, but click it to be sure
                WebElement firstMethod = methods.get(0);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstMethod);
                Thread.sleep(500);
                firstMethod.click();
                
                // Click continue
                wait.until(ExpectedConditions.elementToBeClickable(buttonPaymentMethod));
                driver.findElement(buttonPaymentMethod).click();
                
                // Wait for modal to close and page to update
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addComment(String comment) {
        wait.until(ExpectedConditions.presenceOfElementLocated(commentTextarea));
        driver.findElement(commentTextarea).sendKeys(comment);
        
        // Wait a bit for comment to be saved
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void agreeToTerms() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(agreeTermsCheckbox));
            WebElement agreeCheckbox = driver.findElement(agreeTermsCheckbox);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", agreeCheckbox);
            Thread.sleep(500);
            
            if (!agreeCheckbox.isSelected()) {
                try {
                    agreeCheckbox.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", agreeCheckbox);
                }
            }
            
            // Wait for confirm section to refresh after agreeing
            Thread.sleep(2000);
        } catch (Exception e) {
            // Ignore if checkbox not present
        }
    }

    // ===== ORDER CONFIRMATION METHODS =====
    public void confirmOrder() {
        try {
            // Wait for button to be enabled (all steps must be completed)
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            longWait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
            
            WebElement confirmBtn = driver.findElement(confirmOrderButton);
            
            // Verify button is enabled
            if (!confirmBtn.isEnabled()) {
                throw new RuntimeException("Confirm Order button is disabled. All checkout steps must be completed first.");
            }
            
            // Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmBtn);
            Thread.sleep(1000);
            
            // Click using JavaScript if regular click fails
            try {
                confirmBtn.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
            }
            
            // Wait for order confirmation
            Thread.sleep(3000);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to confirm order: " + e.getMessage(), e);
        }
    }

    public String getOrderTotal() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(orderTotal));
            return driver.findElement(orderTotal).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isConfirmButtonEnabled() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(confirmOrderButton));
            WebElement confirmBtn = driver.findElement(confirmOrderButton);
            return confirmBtn.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isOrderConfirmationSectionDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(checkoutConfirmSection));
            return driver.findElement(checkoutConfirmSection).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void waitForConfirmButtonToBeEnabled() {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            longWait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        } catch (Exception e) {
            // Button might not become enabled if steps are incomplete
        }
    }
    
    public boolean waitForOrderSuccess() {
        WebDriverWait successWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            successWait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("route=checkout/success"),
                ExpectedConditions.presenceOfElementLocated(orderSuccessHeading)
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isOrderSuccessDisplayed() {
        try {
            return driver.findElement(orderSuccessHeading).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

