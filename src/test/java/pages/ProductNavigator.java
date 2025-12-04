package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductNavigator {
    WebDriver driver;
    WebDriverWait wait;

    //                  ===== LOCATORS =====

    //                  ===== Constructor ======
    public ProductNavigator(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //                  ===== ACTIONS =====
    public void openProductById(String productId) {
        driver.get("http://localhost/opencartproject/index.php?route=product/product&language=en-gb&product_id=" + productId);
    }

    //                  ===== ASSERTION  =====
}


