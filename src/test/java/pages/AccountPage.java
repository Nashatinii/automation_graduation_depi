package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccountPage {

    WebDriver driver;
    WebDriverWait wait;

    // ===== LOCATORS =====
    By pageTitle = By.xpath("//h1[contains(text(), 'My Account')]");
    By editAccountLink = By.xpath("//a[contains(@href, 'route=account/edit')]");
    By changePasswordLink = By.xpath("//a[contains(@href, 'route=account/password')]");
    By paymentMethodsLink = By.xpath("//a[contains(@href, 'route=account/payment_method')]");
    By addressBookLink = By.xpath("//a[contains(@href, 'route=account/address')]");
    By wishlistLink = By.xpath("//a[contains(@href, 'route=account/wishlist')]");
    By orderHistoryLink = By.xpath("//a[contains(@href, 'route=account/order') and contains(text(), 'order history')]");
    By subscriptionsLink = By.xpath("//a[contains(@href, 'route=account/subscription')]");
    By downloadsLink = By.xpath("//a[contains(@href, 'route=account/download')]");
    By rewardPointsLink = By.xpath("//a[contains(@href, 'route=account/reward')]");
    By returnsLink = By.xpath("//a[contains(@href, 'route=account/returns')]");
    By transactionsLink = By.xpath("//a[contains(@href, 'route=account/transaction')]");
    By affiliateLink = By.xpath("//a[contains(@href, 'route=account/affiliate')]");
    By newsletterLink = By.xpath("//a[contains(@href, 'route=account/newsletter')]");
    By logoutLink = By.xpath("//a[contains(@href, 'route=account/logout')]");
    
    // Sidebar links
    By sidebarMyAccount = By.xpath("//aside//a[contains(@href, 'route=account/account')]");
    By sidebarEditAccount = By.xpath("//aside//a[contains(@href, 'route=account/edit')]");
    By sidebarPassword = By.xpath("//aside//a[contains(@href, 'route=account/password')]");
    By sidebarWishlist = By.xpath("//aside//a[contains(@href, 'route=account/wishlist')]");
    By sidebarOrderHistory = By.xpath("//aside//a[contains(@href, 'route=account/order')]");
    
    // Top navigation (when logged in)
    By topMyAccountDropdown = By.xpath("//a[contains(@class, 'dropdown-toggle') and .//i[contains(@class,'fa-user')]]");
    By topMyAccountLink = By.xpath("//ul[@class='dropdown-menu']//a[contains(@href, 'route=account/account')]");
    By topOrderHistoryLink = By.xpath("//ul[@class='dropdown-menu']//a[contains(@href, 'route=account/order')]");
    By topTransactionsLink = By.xpath("//ul[@class='dropdown-menu']//a[contains(@href, 'route=account/transaction')]");
    By topDownloadsLink = By.xpath("//ul[@class='dropdown-menu']//a[contains(@href, 'route=account/download')]");
    By topLogoutLink = By.xpath("//ul[@class='dropdown-menu']//a[contains(@href, 'route=account/logout')]");

    // Constructor
    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== VALIDATION METHODS =====
    public boolean isAccountPageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(pageTitle));
            return driver.findElement(pageTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitle() {
        try {
            return driver.findElement(pageTitle).getText();
        } catch (Exception e) {
            return "";
        }
    }

    // ===== NAVIGATION METHODS - Main Content =====
    public void clickEditAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(editAccountLink));
        driver.findElement(editAccountLink).click();
    }

    public void clickChangePassword() {
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordLink));
        driver.findElement(changePasswordLink).click();
    }

    public void clickPaymentMethods() {
        wait.until(ExpectedConditions.elementToBeClickable(paymentMethodsLink));
        driver.findElement(paymentMethodsLink).click();
    }

    public void clickAddressBook() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addressBookLink));
            WebElement addressElement = driver.findElement(addressBookLink);
            addressElement.click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            // If stale, wait and try again
            try {
                Thread.sleep(1000);
                wait.until(ExpectedConditions.elementToBeClickable(addressBookLink));
                WebElement addressElement = driver.findElement(addressBookLink);
                addressElement.click();
            } catch (Exception e2) {
                // Try JavaScript click as fallback
                WebElement addressElement = driver.findElement(addressBookLink);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addressElement);
            }
        }
    }

    public void clickWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(wishlistLink));
        driver.findElement(wishlistLink).click();
    }

    public void clickOrderHistory() {
        wait.until(ExpectedConditions.elementToBeClickable(orderHistoryLink));
        driver.findElement(orderHistoryLink).click();
    }

    public void clickSubscriptions() {
        wait.until(ExpectedConditions.elementToBeClickable(subscriptionsLink));
        driver.findElement(subscriptionsLink).click();
    }

    public void clickDownloads() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadsLink));
        driver.findElement(downloadsLink).click();
    }

    public void clickRewardPoints() {
        wait.until(ExpectedConditions.elementToBeClickable(rewardPointsLink));
        driver.findElement(rewardPointsLink).click();
    }

    public void clickReturns() {
        wait.until(ExpectedConditions.elementToBeClickable(returnsLink));
        driver.findElement(returnsLink).click();
    }

    public void clickTransactions() {
        wait.until(ExpectedConditions.elementToBeClickable(transactionsLink));
        driver.findElement(transactionsLink).click();
    }

    public void clickAffiliate() {
        wait.until(ExpectedConditions.elementToBeClickable(affiliateLink));
        driver.findElement(affiliateLink).click();
    }

    public void clickNewsletter() {
        try {
            // Wait for element to be present and clickable
            wait.until(ExpectedConditions.presenceOfElementLocated(newsletterLink));
            wait.until(ExpectedConditions.elementToBeClickable(newsletterLink));
            
            // Re-find element to avoid stale reference
            WebElement newsletterElement = driver.findElement(newsletterLink);
            newsletterElement.click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            // If stale, wait and try again
            try {
                Thread.sleep(1000);
                wait.until(ExpectedConditions.elementToBeClickable(newsletterLink));
                WebElement newsletterElement = driver.findElement(newsletterLink);
                newsletterElement.click();
            } catch (Exception e2) {
                // Try JavaScript click as fallback
                WebElement newsletterElement = driver.findElement(newsletterLink);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newsletterElement);
            }
        }
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        driver.findElement(logoutLink).click();
    }

    // ===== NAVIGATION METHODS - Top Navigation =====
    public void openMyAccountDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(topMyAccountDropdown));
        driver.findElement(topMyAccountDropdown).click();
    }

    public void clickTopMyAccount() {
        openMyAccountDropdown();
        wait.until(ExpectedConditions.elementToBeClickable(topMyAccountLink));
        driver.findElement(topMyAccountLink).click();
    }

    public void clickTopOrderHistory() {
        openMyAccountDropdown();
        wait.until(ExpectedConditions.elementToBeClickable(topOrderHistoryLink));
        driver.findElement(topOrderHistoryLink).click();
    }

    public void clickTopTransactions() {
        openMyAccountDropdown();
        wait.until(ExpectedConditions.elementToBeClickable(topTransactionsLink));
        driver.findElement(topTransactionsLink).click();
    }

    public void clickTopDownloads() {
        openMyAccountDropdown();
        wait.until(ExpectedConditions.elementToBeClickable(topDownloadsLink));
        driver.findElement(topDownloadsLink).click();
    }

    public void clickTopLogout() {
        openMyAccountDropdown();
        wait.until(ExpectedConditions.elementToBeClickable(topLogoutLink));
        driver.findElement(topLogoutLink).click();
    }

    // ===== NAVIGATION METHODS - Sidebar =====
    public void clickSidebarMyAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(sidebarMyAccount));
        driver.findElement(sidebarMyAccount).click();
    }

    public void clickSidebarEditAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(sidebarEditAccount));
        driver.findElement(sidebarEditAccount).click();
    }

    public void clickSidebarPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(sidebarPassword));
        driver.findElement(sidebarPassword).click();
    }

    public void clickSidebarWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(sidebarWishlist));
        driver.findElement(sidebarWishlist).click();
    }

    public void clickSidebarOrderHistory() {
        wait.until(ExpectedConditions.elementToBeClickable(sidebarOrderHistory));
        driver.findElement(sidebarOrderHistory).click();
    }

    // ===== HELPER METHODS =====
    public boolean isLoggedIn() {
        try {
            // Wait a bit for page to load
            Thread.sleep(1000);
            
            // Check multiple indicators that user is logged in:
            // 1. Account page is displayed
            boolean accountPageVisible = isAccountPageDisplayed();
            
            // 2. URL contains account routes
            String currentUrl = driver.getCurrentUrl();
            boolean hasAccountUrl = currentUrl.contains("account/account") || 
                                  currentUrl.contains("account/order") ||
                                  currentUrl.contains("account/wishlist");
            
            // 3. Logout link is present (try both locations)
            boolean hasLogoutLink = false;
            try {
                hasLogoutLink = driver.findElement(logoutLink).isDisplayed();
            } catch (Exception e) {
                try {
                    hasLogoutLink = driver.findElement(topLogoutLink).isDisplayed();
                } catch (Exception e2) {
                    // Try finding any logout link
                    try {
                        hasLogoutLink = driver.findElements(By.xpath("//a[contains(@href, 'logout')]")).size() > 0;
                    } catch (Exception e3) {
                        // Ignore
                    }
                }
            }
            
            return accountPageVisible || hasAccountUrl || hasLogoutLink;
        } catch (Exception e) {
            // Fallback: check URL
            try {
                String currentUrl = driver.getCurrentUrl();
                return currentUrl.contains("account/") && !currentUrl.contains("account/login") && 
                       !currentUrl.contains("account/register");
            } catch (Exception e2) {
                return false;
            }
        }
    }

    public void logout() {
        if (isLoggedIn()) {
            try {
                clickTopLogout();
            } catch (Exception e) {
                clickLogout();
            }
        }
    }
}

