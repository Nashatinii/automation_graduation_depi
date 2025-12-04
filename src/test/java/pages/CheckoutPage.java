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
import java.util.List;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    //                  ===== LOCATORS =====
    By cartDropdownButton = By.xpath("//button[@class=\"btn btn-lg btn-dark d-block dropdown-toggle\"]");
    By checkoutLink = By.xpath("//a[@title=\"Checkout\"]");


    By checkoutTitle = By.xpath("//h1[contains(text(),\"Checkout\")]");
    By shippingExistingRadio = By.id("input-shipping-existing");
    By shippingNewRadio = By.id("input-shipping-new");
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
    By buttonShippingMethods = By.id("button-shipping-methods");
    By modalShipping = By.id("modal-shipping");
    By shippingMethodRadioModal = By.xpath("//div[@id='modal-shipping']//input[@name='shipping_method']");
    By buttonShippingMethod = By.id("button-shipping-method");
    By buttonPaymentMethods = By.id("button-payment-methods");
    By modalPayment = By.id("modal-payment");
    By paymentMethodRadioModal = By.xpath("//div[@id='modal-payment']//input[@name='payment_method']");
    By buttonPaymentMethod = By.id("button-payment-method");
    By commentTextarea = By.id("input-comment");
    By agreeTermsCheckbox = By.id("input-checkout-agree");
    By confirmOrderButton = By.id("button-confirm");
    By orderSuccessTitle = By.xpath("//h1[contains(text(),\"Your order has been placed\")]");
    By AlertMessage = By.xpath("//*[@id=\"alert\"]");


    // ===== LOCATORS للـ Guest Checkout =====
    By guestCheckoutRadio = By.id("input-guest");
    By registerAccountRadio = By.id("input-register");
    By guestFirstName = By.id("input-firstname");  // ده للـ Guest
    By guestLastName = By.id("input-lastname");    // ده للـ Guest
    By emailField = By.id("input-email");
    By guestShippingCompany = By.id("input-shipping-company");
    By guestShippingAddress1 = By.id("input-shipping-address-1");
    By guestShippingAddress2 = By.id("input-shipping-address-2");
    By guestShippingCity = By.id("input-shipping-city");
    By guestShippingPostcode = By.id("input-shipping-postcode");
    By guestShippingCountry = By.id("input-shipping-country");
    By guestShippingZone = By.id("input-shipping-zone");
    By continueButton = By.id("button-register");


    //                  ===== Constructor ======
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //                  ===== ACTIONS =====


    public void opencheckoutPage() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutLink));
        driver.findElement(checkoutLink).click();
    }






//---------------------------------------------------------------------------------------------------------=---------//

    // ===== ACTIONS للـ Guest Checkout =====

    /**
     * اختيار Guest Checkout من الراديو بتون
     */
    public void selectGuestCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(guestCheckoutRadio));
        driver.findElement(guestCheckoutRadio).click();
        // استنى شوية علشان الفورم يظهر
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ملء البيانات الشخصية للـ Guest (First Name, Last Name, Email)
     */
    public void fillGuestPersonalDetails(String firstName, String lastName, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(guestFirstName));

        driver.findElement(guestFirstName).clear();
        driver.findElement(guestFirstName).sendKeys(firstName);

        driver.findElement(guestLastName).clear();
        driver.findElement(guestLastName).sendKeys(lastName);

        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    /**
     * ملء عنوان الشحن للـ Guest
     */
    public void fillGuestShippingAddress(String company, String address1, String address2,
                                         String city, String postcode, String country, String zone) {
        if (company != null && !company.isEmpty()) {
            driver.findElement(guestShippingCompany).clear();
            driver.findElement(guestShippingCompany).sendKeys(company);
        }

        driver.findElement(guestShippingAddress1).clear();
        driver.findElement(guestShippingAddress1).sendKeys(address1);

        if (address2 != null && !address2.isEmpty()) {
            driver.findElement(guestShippingAddress2).clear();
            driver.findElement(guestShippingAddress2).sendKeys(address2);
        }

        driver.findElement(guestShippingCity).clear();
        driver.findElement(guestShippingCity).sendKeys(city);

        driver.findElement(guestShippingPostcode).clear();
        driver.findElement(guestShippingPostcode).sendKeys(postcode);

        // اختر الدولة
        Select countrySelect = new Select(driver.findElement(guestShippingCountry));
        countrySelect.selectByVisibleText(country);

        // استنى الـ Zone dropdown يتحمل
        try {
            Thread.sleep(1000); // استنى ثانية واحدة
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // دلوقتي اختر الـ Zone
        wait.until(ExpectedConditions.presenceOfElementLocated(guestShippingZone));
        Select zoneSelect = new Select(driver.findElement(guestShippingZone));
        zoneSelect.selectByVisibleText(zone);
    }

    /**
     * الضغط على زرار Continue بعد ملء البيانات
     */
    /**
     * الضغط على زرار Continue بعد ملء البيانات
     */
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        WebElement continueBtn = driver.findElement(continueButton);

        // استخدم JavaScript للضغط بدل الـ click العادي
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", continueBtn);

        // استنى شوية بعد الـ scroll
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // اضغط باستخدام JavaScript
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
    }

    /**
     * Complete Guest Checkout - function واحدة تعمل كل حاجة
     */
    public void completeGuestCheckoutForm(String firstName, String lastName, String email,
                                          String company, String address1, String address2,
                                          String city, String postcode, String country, String zone) {
        selectGuestCheckout();
        fillGuestPersonalDetails(firstName, lastName, email);
        fillGuestShippingAddress(company, address1, address2, city, postcode, country, zone);
        clickContinueButton();
    }



//---------------------------------------------------------------------------------------------------------=---------//




    public void selectExistingShippingAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(shippingExistingRadio));
        driver.findElement(shippingExistingRadio).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(shippingAddressSelect));
        Select addressSelect = new Select(driver.findElement(shippingAddressSelect));
        if (addressSelect.getOptions().size() > 1) {
            addressSelect.selectByIndex(1);
        }
        wait.until(ExpectedConditions.elementToBeClickable(buttonShippingAddress));
        driver.findElement(buttonShippingAddress).click();}



    public void selectExistingShippingAddressByIndex(int index) {
        // اضغط على اختيار Existing Address
        wait.until(ExpectedConditions.elementToBeClickable(shippingExistingRadio));
        driver.findElement(shippingExistingRadio).click();

        // استنى الدروب داون يظهر
        wait.until(ExpectedConditions.presenceOfElementLocated(shippingAddressSelect));
        Select addressSelect = new Select(driver.findElement(shippingAddressSelect));

        // تأكد إن الاندكس صالح
        int optionsCount = addressSelect.getOptions().size();
        if (index >= 0 && index < optionsCount) {
            addressSelect.selectByIndex(index);
        } else {
            System.out.println("⚠ Invalid address index: " + index + " — available options: " + optionsCount);
        }

    }



    public void fillNewShippingAddress(String firstName, String lastName, String company, String address1, String address2, String city, String postcode, String country, String zone) {
        wait.until(ExpectedConditions.elementToBeClickable(shippingNewRadio));
        driver.findElement(shippingNewRadio).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(shippingFirstName));
        driver.findElement(shippingFirstName).clear();
        driver.findElement(shippingFirstName).sendKeys(firstName);
        driver.findElement(shippingLastName).clear();
        driver.findElement(shippingLastName).sendKeys(lastName);
        if (company != null && !company.isEmpty()) {
            driver.findElement(shippingCompany).clear();
            driver.findElement(shippingCompany).sendKeys(company);
        }
        driver.findElement(shippingAddress1).clear();
        driver.findElement(shippingAddress1).sendKeys(address1);
        if (address2 != null && !address2.isEmpty()) {
            driver.findElement(shippingAddress2).clear();
            driver.findElement(shippingAddress2).sendKeys(address2);
        }

        waitAndClickWithJS(shippingCity);
        driver.findElement(shippingCity).clear();
        driver.findElement(shippingCity).sendKeys(city);
        driver.findElement(shippingPostcode).clear();
        driver.findElement(shippingPostcode).sendKeys(postcode);
        Select countrySelect = new Select(driver.findElement(shippingCountry));
        countrySelect.selectByVisibleText(country);
        wait.until(ExpectedConditions.presenceOfElementLocated(shippingZone));
        Select zoneSelect = new Select(driver.findElement(shippingZone));
        zoneSelect.selectByVisibleText(zone);
        wait.until(ExpectedConditions.elementToBeClickable(buttonShippingAddress));
        driver.findElement(buttonShippingAddress).click();
    }



    public void chooseShippingMethod() {
//        wait.until(ExpectedConditions.elementToBeClickable(buttonShippingMethods));
//        driver.findElement(buttonShippingMethods).click();
        waitAndClickWithJS(buttonShippingMethods);
        wait.until(ExpectedConditions.presenceOfElementLocated(modalShipping));}
    public void selectShippingMethodInModal(String methodValue) {
        wait.until(ExpectedConditions.presenceOfElementLocated(shippingMethodRadioModal));
        java.util.List<WebElement> methods = driver.findElements(shippingMethodRadioModal);
        for (WebElement method : methods) {
            if (method.getAttribute("value").equals(methodValue)) {
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",method);
                wait.until(ExpectedConditions.elementToBeClickable(method));
                method.click();
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(buttonShippingMethod));
        driver.findElement(buttonShippingMethod).click();}

    public void selectShippingMethod() {
        chooseShippingMethod();
        wait.until(ExpectedConditions.presenceOfElementLocated(shippingMethodRadioModal));
        java.util.List<WebElement> methods = driver.findElements(shippingMethodRadioModal);
        if (!methods.isEmpty()) {
            WebElement firstMethod = methods.get(0);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",firstMethod);
            wait.until(ExpectedConditions.elementToBeClickable(firstMethod));
            firstMethod.click();
            wait.until(ExpectedConditions.elementToBeClickable(buttonShippingMethod));
            driver.findElement(buttonShippingMethod).click();
        }
    }
    public void choosePaymentMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonPaymentMethods));
        driver.findElement(buttonPaymentMethods).click();
//        waitAndClickWithJS(buttonPaymentMethod);
        wait.until(ExpectedConditions.presenceOfElementLocated(modalPayment));}
    public void selectPaymentMethodInModal(String methodValue) {
        wait.until(ExpectedConditions.presenceOfElementLocated(paymentMethodRadioModal));
        java.util.List<WebElement> methods = driver.findElements(paymentMethodRadioModal);
        for (WebElement method : methods) {
            if (method.getAttribute("value").equals(methodValue)) {
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",method);
                wait.until(ExpectedConditions.elementToBeClickable(method));
                method.click();
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(buttonPaymentMethod));
        driver.findElement(buttonPaymentMethod).click();}
    public void selectPaymentMethod() {
        choosePaymentMethod();
        wait.until(ExpectedConditions.presenceOfElementLocated(paymentMethodRadioModal));
        java.util.List<WebElement> methods = driver.findElements(paymentMethodRadioModal);
        if (!methods.isEmpty()) {
            WebElement firstMethod = methods.get(0);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",firstMethod);
            wait.until(ExpectedConditions.elementToBeClickable(firstMethod));
            firstMethod.click();
            wait.until(ExpectedConditions.elementToBeClickable(buttonPaymentMethod));
            driver.findElement(buttonPaymentMethod).click();
        }
    }
    public void addComment(String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextarea));
        driver.findElement(commentTextarea).clear();
        driver.findElement(commentTextarea).sendKeys(comment);}
    public void agreeToTerms() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(agreeTermsCheckbox));
            WebElement agreeCheckbox = driver.findElement(agreeTermsCheckbox);
            if (!agreeCheckbox.isSelected()) {
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",agreeCheckbox);
                wait.until(ExpectedConditions.elementToBeClickable(agreeCheckbox));
                agreeCheckbox.click();
            }
        } catch (Exception e) {
        }
    }
    public void confirmOrder() {
//        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
//        WebElement confirm_btn = driver.findElement(confirmOrderButton);
//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",confirm_btn);
////        wait.until(ExpectedConditions.elementToBeEnabled(confirmOrderButton));
//        confirm_btn.click();
        waitAndClickWithJS(confirmOrderButton);
        }

    //                  ===== ASSERTION  =====
    public void Assertion_checkout_page_opened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTitle));
        Assert.assertTrue(driver.findElement(checkoutTitle).isDisplayed());}
    public void Assertion_billing_details_saved() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonShippingMethods));
        Assert.assertTrue(driver.findElement(buttonShippingMethods).isDisplayed());}
    public void Assertion_order_confirmed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessTitle));
        Assert.assertTrue(driver.findElement(orderSuccessTitle).isDisplayed());}


    // Helper method
    private void waitAndClickWithJS(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = driver.findElement(locator);

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }
}




