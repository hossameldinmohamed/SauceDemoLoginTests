package Pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Test data
    private final String BASE_URL = "https://www.saucedemo.com/";



    // Locators
    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By PRODUCTS_TITLE = By.className("title");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");




    @Step("Navigate to SauceDemo login page")
    public void navigateToLoginPage() {
        setUp();
        Allure.addAttachment("Navigated to URL", BASE_URL);
    }

    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
        usernameField.clear();
        usernameField.sendKeys(username);
        Allure.addAttachment("Username entered", username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
        passwordField.clear();
        passwordField.sendKeys(password);
        Allure.addAttachment("Password", "***");
    }

    @Step("Click login button")
    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        loginButton.click();
    }

    @Step("Verify successful login")
    public void verifySuccessfulLogin() {
        WebElement productsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTS_TITLE));
        Assert.assertTrue(productsTitle.isDisplayed(), "Products page should be displayed after successful login");
        Assert.assertEquals(productsTitle.getText(), "Products", "Page title should be 'Products'");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "URL should contain 'inventory.html' after successful login");

        Allure.addAttachment("Current URL", currentUrl);
        Allure.addAttachment("Login Status", "SUCCESS");
    }

    @Step("Verify error message: {expectedMessage}")
    public void verifyErrorMessage(String expectedMessage) {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
        String actualMessage = errorElement.getText();

        Assert.assertTrue(errorElement.isDisplayed(), "Error message should be displayed");
        Assert.assertEquals(actualMessage, expectedMessage, "Error message should match expected text");

        Allure.addAttachment("Expected Error", expectedMessage);
        Allure.addAttachment("Actual Error", actualMessage);
        Allure.addAttachment("Login Status", "FAILED");
    }



    @Step("Setup browser and navigate to SauceDemo")
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode for CI/CD
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(BASE_URL);
        Allure.addAttachment("Test URL", BASE_URL);
    }


    @Step("Close browser")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
