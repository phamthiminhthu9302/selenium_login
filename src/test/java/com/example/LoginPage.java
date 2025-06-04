package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
  WebDriver driver;

  By emailInput = By.id("email");
  By passwordInput = By.id("password");
  By loginBtn = By.xpath("//button[contains(text(),'Login')]");
  By eyeBtn = By.cssSelector("button.show");
  By emailErr = By.xpath("//span[contains(text(), 'Email không được bỏ trống')]");
  By passwordErr = By.xpath("//span[contains(text(), 'Password không được bỏ trống !')]");
  By toastify = By.xpath("//div[contains(@class, 'Toastify__toast-body')]");
  By registerLink = By.xpath("//a[@href='/register' and text()='Register now ?']");
  By loginLink = By.xpath("//ul[contains(@class,'ul')]//a[@href='/login' and text()='Sign in']");
  By nameInput = By.id("name");
  By confirmPasswordInput = By.name("passwordConfirm");
  By phoneInput = By.id("phone");
  By birthday = By.id("birthday");
  By gender_male = By.cssSelector("label[for='male']");
  By gender_female = By.cssSelector("label[for='female']");
  By agreeCheckbox = By.id("agree-term");
  By submitBtn = By.className("btn_register");

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  public String getToastifyErrorMessage() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toastify));
    return errorElement.getText();
  }

  public String getToastifySuccessMessage() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement susscessElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toastify));
    return susscessElement.getText();
  }

  public void clickRegisterLink() {
    driver.findElement(registerLink).click();
  }

  public void clickLoginLink() {
    driver.findElement(loginLink).click();
  }

  public boolean getDisplayedLogin() {
    return driver.findElement(emailInput).isDisplayed() && driver.findElement(passwordInput).isDisplayed()
        && driver.findElement(eyeBtn).isDisplayed() && driver.findElement(loginBtn).isDisplayed();
  }

  public boolean getDisplayedRegister() {
    return driver.findElement(nameInput).isDisplayed() &&
        driver.findElement(emailInput).isDisplayed() &&
        driver.findElement(passwordInput).isDisplayed() &&
        driver.findElement(eyeBtn).isDisplayed() &&
        driver.findElement(confirmPasswordInput).isDisplayed() &&
        driver.findElement(phoneInput).isDisplayed() &&
        driver.findElement(birthday).isDisplayed() &&
        driver.findElement(gender_male).isDisplayed() &&
        driver.findElement(gender_female).isDisplayed() &&
        driver.findElement(agreeCheckbox).isDisplayed() &&
        driver.findElement(submitBtn).isDisplayed();
  }

  public void setValuePassword(String password) {
    driver.findElement(passwordInput).sendKeys(password);
  }

  public String getTypePassword() {
    return driver.findElement(passwordInput).getAttribute("type");
  }

  public boolean getDisplayedEmailErr() {
    return driver.findElement(emailErr).isDisplayed();
  }

  public boolean getDisplayedPasswordError() {
    return driver.findElement(passwordErr).isDisplayed();
  }

  public void enterLoginCredentials(String email, String password) {
    clickLoginLink();
    driver.findElement(emailInput).sendKeys(email);
    driver.findElement(passwordInput).sendKeys(password);
    driver.findElement(loginBtn).click();
  }

  public void clickEye() {
    driver.findElement(eyeBtn).click();
  }
}
