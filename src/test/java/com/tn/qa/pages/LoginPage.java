package com.tn.qa.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import enums.LoginMessagesEnum;
import enums.TestdataValidityEnum;

public class LoginPage {

	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();
	
	@FindBy(css = "input#input-email")
	private WebElement username;
	
	@FindBy(css = "input#input-password")
	private WebElement password;
	
	@FindBy(css = "input.btn.btn-primary")
	private WebElement loginButton;
	
	@FindBy(linkText = "Logout")
	private WebElement logout;
	
	@FindBy(css = "div.alert.alert-danger.alert-dismissible")
	private WebElement warningText;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void inputEmail(String user) {
		username.sendKeys(user);
	}
	
	public void inputPassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void clickoOnLoginButton() {
		loginButton.click();
	}
	
	public boolean logOutButtonDisplayed() {
		return logout.isDisplayed();
	}
	
	public String getWarningText() {
		return warningText.getText();
	}
	
	public void loginWithUsernamePassword(String username, String password) {
		inputEmail(username);
		inputPassword(password);
		clickoOnLoginButton();
	}
	
	public void validateLogin(String testdataValidity) {
		if (testdataValidity==TestdataValidityEnum.VALID_USERNAME_VALID_PASSWORD.testdataValidity.toString()) {
		softassert.assertTrue(logOutButtonDisplayed());
		softassert.assertAll();
		System.out.println("Successfully validated login with valid credentials.");
		} else {
			String actualWarningMessage = getWarningText();
			String expectedWarningMessage = LoginMessagesEnum.WRONG_CREDENTIAL_MESSAGE.loginMessage.toString();
			
			softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning message is not correct");
			softassert.assertAll();
			System.out.println("Successfully validated login with invalid credential scenarios");
		} 
	}
	
}
