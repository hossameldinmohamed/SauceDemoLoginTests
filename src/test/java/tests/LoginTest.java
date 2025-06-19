package tests;

import Pages.LoginPage;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

@Epic("SauceDemo Authentication")
@Feature("Login Functionality")
public class LoginTest {

    private WebDriver driver;

    LoginPage loginPage;

//
//    // Test data

    private final String VALID_USERNAME = "standard_user";
    private final String VALID_PASSWORD = "secret_sauce";
    private final String INVALID_USERNAME = "invalid_user";
    private final String INVALID_PASSWORD = "invalid_password";



    @BeforeMethod
    @Step("Setup browser and navigate to SauceDemo")
    public void setUp() {

//        ChromeOptions options = new ChromeOptions();
//       // options.addArguments("--headless"); // Run in headless mode for CI/CD
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--window-size=1920,1080");
//
//        driver = new ChromeDriver(options);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        driver.get(BASE_URL);
//        Allure.addAttachment("Test URL", BASE_URL);
       loginPage = new LoginPage();
        loginPage.navigateToLoginPage();

    }

    @Test(description = "Successful login with valid credentials")
    @Story("Valid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Test successful login")
    public void testSuccessfulLogin() {
        // Enter username
        loginPage.enterUsername(VALID_USERNAME);

        // Enter password
        loginPage.enterPassword(VALID_PASSWORD);

        // Click login button
        loginPage.clickLoginButton();

        // Verify successful login
        loginPage.verifySuccessfulLogin();
    }

    @Test(description = "Login attempt with invalid username")
    @Story("Invalid Login")
    @Severity(SeverityLevel.NORMAL)
    @Step("Test login with invalid username")
    public void testInvalidUsername() {
        // Enter invalid username
        loginPage.enterUsername(INVALID_USERNAME);

        // Enter valid password
        loginPage.enterPassword(VALID_PASSWORD);

        // Click login button
        loginPage.clickLoginButton();

        // Verify error message
        loginPage.verifyErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

    @Test(description = "Login attempt with invalid password")
    @Story("Invalid Login")
    @Severity(SeverityLevel.NORMAL)
    @Step("Test login with invalid password")
    public void testInvalidPassword() {
        // Enter valid username
        loginPage.enterUsername(VALID_USERNAME);

        // Enter invalid password
        loginPage.enterPassword(INVALID_PASSWORD);

        // Click login button
        loginPage.clickLoginButton();

        // Verify error message
        loginPage.verifyErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

    @Test(description = "Login attempt with empty credentials")
    @Story("Invalid Login")
    @Severity(SeverityLevel.NORMAL)
    @Step("Test login with empty credentials")
    public void testEmptyCredentials() {
        // Click login button without entering credentials
        loginPage.clickLoginButton();

        // Verify error message
        loginPage.verifyErrorMessage("Epic sadface: Username is required");
    }

    @Test(description = "Login attempt with empty password")
    @Story("Invalid Login")
    @Severity(SeverityLevel.NORMAL)
    @Step("Test login with empty password")
    public void testEmptyPassword() {
        // Enter username
        loginPage.enterUsername(VALID_USERNAME);

        // Click login button without password
        loginPage.clickLoginButton();

        // Verify error message
        loginPage.verifyErrorMessage("Epic sadface: Password is required");
    }


    @AfterMethod
    @Step("Close browser")
    public void tearDown() {
       loginPage.tearDown();
    }
}