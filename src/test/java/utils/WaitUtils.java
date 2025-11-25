package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 10;

    public static void waitForElementToBeVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementToBeClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBePresent(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForTextToBePresent(WebDriver driver, By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static void waitForUrlToContain(WebDriver driver, String urlPart) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.urlContains(urlPart));
    }

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}




