package com.tn.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import enums.RegistrationPageEnums;

public class RegisterPage {

	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();

	@FindBy(css = "input#input-firstname")
	private WebElement firstname;

	@FindBy(css = "input#input-lastname")
	private WebElement lastname;

	@FindBy(css = "input#input-email")
	private WebElement email;

	@FindBy(css = "input#input-telephone")
	private WebElement telephone;

	@FindBy(css = "input#input-password")
	private WebElement password;

	@FindBy(css = "input#input-confirm")
	private WebElement confPassword;

	@FindBy(css = "input.btn.btn-primary")
	private WebElement contButton;

	@FindBy(css = ".form-horizontal .radio-inline:nth-of-type(1) [type]")
	private WebElement subsButton;

	@FindBy(css = "input[name='agree']")
	private WebElement privacyCheckbox;

	@FindBy(css = "div#content > h1")
	private WebElement accCreatedMsg;

	@FindBy(css = "#account .has-error:nth-child(5) .text-danger")
	private WebElement emailErrorMsg;

	@FindBy(css = ".form-horizontal fieldset:nth-of-type(2) .text-danger")
	private WebElement passErrorMsg;
	
	@FindBy(css = "#account .has-error:nth-child(3) .text-danger")
	private WebElement fnameErrorMsg;

	@FindBy(css = "#account .has-error:nth-child(4) .text-danger")
	private WebElement lnameErrorMsg;

	@FindBy(css = "#account .has-error:nth-child(6) .text-danger")
	private WebElement phoneErrorMsg;
	
	@FindBy(css = "div.alert.alert-danger.alert-dismissible")
	private WebElement warningText;
	

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void inputFirstname(String fname) {
		firstname.sendKeys(fname);
	}

	public void inputLastname(String lname) {
		lastname.sendKeys(lname);
	}

	public void inputEmail(String emailaddress) {
		email.sendKeys(emailaddress);
	}

	public void inputPhone(String phone) {
		telephone.sendKeys(phone);
	}

	public void inputPassword(String pass) {
		password.sendKeys(pass);
	}

	public void inputConfPassword(String cpass) {
		confPassword.sendKeys(cpass);
	}

	public boolean continueButtonDisplayed() {
		return contButton.isDisplayed();
	}

	public void clickSubsButton() {
		subsButton.click();
	}

	public void clickPrivacyCheckbox() {
		privacyCheckbox.click();
	}

	public void clickContinueButton() {
		contButton.click();
	}

	public String getAccCreatedMsg() {
		return accCreatedMsg.getText();
	}
	
	public String getFnameErrMsg() {
		return fnameErrorMsg.getText();
	}
	
	public String getLnameErrMsg() {
		return lnameErrorMsg.getText();
	}
	
	public String getEmailErrMsg() {
		return emailErrorMsg.getText();
	}
	
	public String getPhoneErrMsg() {
		return phoneErrorMsg.getText();
	}
	
	public String getPassErrMsg() {
		return passErrorMsg.getText();
	}
	
	public String getWarningText() {
		return warningText.getText();
	}
	

	public void registerWithCredentials(String firstName, String lastName, String email, String telephone,
			String password, String confpassword, boolean continueButtonDisplayed, boolean agreePrivacyCheckbox) {

		inputFirstname(firstName);
		inputLastname(lastName);
		inputEmail(email);
		inputPhone(telephone);
		inputPassword(password);
		inputConfPassword(confpassword);

		if (continueButtonDisplayed == true) {
			boolean contBtnDisplayed = continueButtonDisplayed();
			softassert.assertEquals(contBtnDisplayed, true, "Continue button is not displayed");
		}

		clickSubsButton();
		if (agreePrivacyCheckbox == true) {
			clickPrivacyCheckbox();
		}
		clickContinueButton();
	}

	public void assertRegistrationDetails(String credentialsValidity) {
		if (credentialsValidity == RegistrationPageEnums.VALID_USERNAME_VALID_PASSWORD.credentialValidity.toString()) {
			String actualAccCreationConfMessage = getAccCreatedMsg();
			String expectedAccCreationConfMessage = "Your Account Has Been Created!";
			softassert.assertEquals(actualAccCreationConfMessage, expectedAccCreationConfMessage);
			softassert.assertAll();
			System.out.println("Successfully validated registration with valid credentials.");

		} else if (credentialsValidity == RegistrationPageEnums.INVALID_USERNAME_INVALID_PASSWORD.toString()) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			String actualEmailWarningMessage = getEmailErrMsg();
			String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
			softassert.assertEquals(actualEmailWarningMessage, expectedEmailWarningMessage);
			String actualPasswordWarningMessage = getPassErrMsg();
			String expectePassworddWarningMessage = "Password must be between 4 and 20 characters!";
			softassert.assertEquals(actualPasswordWarningMessage, expectePassworddWarningMessage);
			softassert.assertAll();
			System.out.println("Successfully validated registration with invalid credentials.");
		} else if (credentialsValidity == RegistrationPageEnums.REREGISTER_VALID_USERNAME_VALID_PASSWORD.credentialValidity
				.toString()) {
			String actualWarningMessage = getWarningText();
			String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
			softassert.assertEquals(actualWarningMessage, expectedWarningMessage);
			softassert.assertAll();
			System.out.println("Successfully validated Re-registration with valid credentials.");
		} else if (credentialsValidity == RegistrationPageEnums.VALID_USERNAME_INVALID_CONF_PASSWORD.credentialValidity
				.toString()) {

			String actualWarnigMessage = getWarningText();
			String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
			softassert.assertEquals(actualWarnigMessage, expectedWarningMessage);
			String actualPasswordConfText = getPassErrMsg();
			String expectedPasswordConfText = "Password confirmation does not match password!";
			softassert.assertEquals(actualPasswordConfText, expectedPasswordConfText);
			softassert.assertAll();
			System.out.println(
					"Successfully validated registration with valid username and wrong confirmation password.");
		} else if (credentialsValidity == RegistrationPageEnums.REGISTRATION_EMPTY_FIELDS.credentialValidity
				.toString()) {

			String actualFirstNameInputWaringText = getFnameErrMsg();
			String expectedFirstNameInputWarningText = "First Name must be between 1 and 32 characters!";
			softassert.assertTrue(actualFirstNameInputWaringText.equals(expectedFirstNameInputWarningText),
					"Warning message is not correct");
			String actualEmailInputWarningText = getEmailErrMsg();
			String expectedEmailInputWarningText = "E-Mail Address does not appear to be valid!";
			softassert.assertTrue(actualEmailInputWarningText.equals(expectedEmailInputWarningText),
					"Warning message is not correct");
			String actualPasswordInputWarningText =getPassErrMsg();
			String expextedPasswordInputWarningText = "Password must be between 4 and 20 characters!";
			softassert.assertTrue(actualPasswordInputWarningText.equals(expextedPasswordInputWarningText),
					"Warning message is not correct");
			softassert.assertAll();
			System.out.println("Successfully validated registration with empty fields.");
		} else if (credentialsValidity == RegistrationPageEnums.REG_EMPT_FNAME_LNAME_VALID_USER_PASS.credentialValidity
				.toString()) {
			String actualFirstNameInputWaringText = getFnameErrMsg();
			String expectedFirstNameInputWarningText = "First Name must be between 1 and 32 characters!";
			softassert.assertTrue(actualFirstNameInputWaringText.equals(expectedFirstNameInputWarningText),
					"Warning message is not correct");
			String actualLastNameInputWaringText = getLnameErrMsg();
			String expectedLastNameInputWaringText = "Last Name must be between 1 and 32 characters!";
			softassert.assertTrue(actualLastNameInputWaringText.equals(expectedLastNameInputWaringText),
					"Warning message is not correct");
			softassert.assertAll();
			System.out.println(
					"Successfully validated registration with empty first name and last name fields with valid username and password.");
		} else if (credentialsValidity == RegistrationPageEnums.VALID_USERNAME_INVALID_CONF_PASSWORD.credentialValidity
				.toString()) {
			String actualEmailWarnigMessage = getEmailErrMsg();
			String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
			softassert.assertEquals(actualEmailWarnigMessage, expectedEmailWarningMessage);
			String actualPhoneWarningMessage = getPhoneErrMsg();
			String expectedPhoneWarningMessage = "Telephone must be between 3 and 32 characters!";
			softassert.assertEquals(actualPhoneWarningMessage, expectedPhoneWarningMessage);
			softassert.assertAll();
			System.out.println(
					"Successfully validated registration with empty username and empty telephone fields with valid first name, last name and password");
		} else if (credentialsValidity == RegistrationPageEnums.REG_EMPT_PHONE_EMPT_EMAIL.credentialValidity
				.toString()) {
			String actualEmailWarningMessage = getEmailErrMsg();;
			String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
			softassert.assertEquals(actualEmailWarningMessage, expectedEmailWarningMessage);

			String actualPhoneWarningMessage = getPhoneErrMsg();
			String expectedPhoneWarningMessage = "Telephone must be between 3 and 32 characters!";
			softassert.assertEquals(actualPhoneWarningMessage, expectedPhoneWarningMessage);

			softassert.assertAll();
			System.out.println(
					"Successfully validated registration with empty username and empty telephone fields with valid first name, last name and password");
		} else if (credentialsValidity == RegistrationPageEnums.REG_WITHOUT_PVC_PLC_CHKBOX.credentialValidity
				.toString()) {
			String actualWarningText = getWarningText();
			String expectedWarningText = "Warning: You must agree to the Privacy Policy!";
			softassert.assertEquals(actualWarningText, expectedWarningText);
			softassert.assertAll();
			System.out.println(
					"Successfully validated registration with valid credentials without clicking privacypolicy checkbox.");
		} else {
			softassert.fail("Unknown testdata provided! Please check the testdata. ");
		}
	}

}
