package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.time.Duration;

public class test_base {

    protected WebDriver driver;
    protected RegisterPage register;
    protected HomePage home;
    protected LoginPage login;
    protected SearchPage search;
    protected ProductPage product;
    protected AccountPage account;
    protected CartPage cart;
    protected CheckoutPage checkout;
    protected CategoryPage category;

    @BeforeMethod
    public void setUp() {
        // Use WebDriverManager to automatically manage driver binaries
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Initialize Pages
        home = new HomePage(driver);
        register = new RegisterPage(driver);
        login = new LoginPage(driver);
        search = new SearchPage(driver);
        product = new ProductPage(driver);
        account = new AccountPage(driver);
        cart = new CartPage(driver);
        checkout = new CheckoutPage(driver);
        category = new CategoryPage(driver);

        // Navigate to Home Page (Your Local Project)
        driver.get("http://localhost/opencartproject/index.php?route=account/logout&language=en-gb");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Helper method to login with existing account
    protected void loginWithExistingAccount() {
        home.openLoginPage();
        login.login("mohamed@gmail.com", "mohamednashat");
    }
}
