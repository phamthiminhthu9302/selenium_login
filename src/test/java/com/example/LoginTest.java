package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class LoginTest {

  public WebDriver driver;
  LoginPage loginPage;
  HomePage homePage;

  @BeforeMethod
  public void setup() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://demo5.cybersoft.edu.vn/");
  }

  @Test
  public void verifySuccessfulLogin_RedirectsToHomePage() throws InterruptedException {
    loginPage = new LoginPage(driver);
    loginPage.enterLoginCredentials("panda@yopmail.com", "123456");
    Thread.sleep(2000);
    Assert.assertEquals(loginPage.getToastifySuccessMessage(), "Đăng nhập tài khoản thành công !",
        "Expected success toast message was not displayed.");
    Assert.assertEquals(driver.getCurrentUrl(), "https://demo5.cybersoft.edu.vn/",
        "User was not redirected to the home page after successful login.");
  }

  @Test
  public void verifyValidationErrorsForMissingRequiredFields() throws InterruptedException {
    loginPage = new LoginPage(driver);
    loginPage.enterLoginCredentials("", "");
    Thread.sleep(2000);
    Assert.assertTrue(loginPage.getDisplayedEmailErr(), "Email required error message is not displayed.");
    Assert.assertTrue(loginPage.getDisplayedPasswordError(), "Password required error message is not displayed.");
  }

  @Test
  public void verifyErrorForInvalidEmailOrPassword() throws InterruptedException {
    loginPage = new LoginPage(driver);
    loginPage.enterLoginCredentials("panda@yopmail.com", "123456789");
    Assert.assertEquals(loginPage.getToastifyErrorMessage(), "Email hoặc mật khẩu không đúng !",
        "Expected error message for invalid credentials is not displayed.");
  }

  @Test
  public void verifyLoginFormIsDisplayedOnClickingSignIn() throws InterruptedException {
    loginPage = new LoginPage(driver);
    loginPage.clickLoginLink();
    Thread.sleep(2000);
    Assert.assertTrue(loginPage.getDisplayedLogin(), "Login form is not displayed as expected.");
  }

  @Test
  public void verifyRedirectToRegistrationPageFromLoginForm() throws InterruptedException {
    loginPage = new LoginPage(driver);
    loginPage.clickLoginLink();
    Thread.sleep(2000);
    loginPage.clickRegisterLink();
    Thread.sleep(2000);
    Assert.assertEquals(driver.getCurrentUrl(), "https://demo5.cybersoft.edu.vn/register",
        "User was not redirected to the registration page after clicking the register link.");
  }

  @Test
  public void verifyUserSessionPersistsAcrossPages() throws InterruptedException {
    loginPage = new LoginPage(driver);
    loginPage.enterLoginCredentials("panda@yopmail.com", "123456");
    homePage = new HomePage(driver);
    Thread.sleep(2000);
    Assert.assertTrue(homePage.isUserLoggedInIndicatorDisplayed(),
        "User login status is not visible on the initial page.");
    homePage.navigateToGraphicsDesignPage();
    Thread.sleep(2000);
    Assert.assertTrue(homePage.isUserLoggedInIndicatorDisplayed(),
        "User login status is not visible after navigating to a different page.");
  }

  @Test
  public void verifyPasswordVisibilityToggleWithEyeIcon() throws InterruptedException {
    loginPage = new LoginPage(driver);
    loginPage.clickLoginLink();
    Thread.sleep(2000);
    loginPage.setValuePassword("123456789");
    Assert.assertEquals(loginPage.getTypePassword(), "password",
        "Password input type should initially be 'password'.");
    loginPage.clickEye();
    Thread.sleep(2000);
    Assert.assertEquals(loginPage.getTypePassword(), "text",
        "Password input type should be 'text' after clicking eye icon");
    loginPage.clickEye();
    Thread.sleep(2000);
    Assert.assertEquals(loginPage.getTypePassword(), "password",
        "Password input type should be 'password' after clicking the eye icon again.");
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
