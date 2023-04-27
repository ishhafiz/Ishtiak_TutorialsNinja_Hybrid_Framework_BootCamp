package com.tn.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.tn.qa.pages.LandingPage;
import com.tn.qa.pages.RegisterPage;
import com.tn.qa.testbase.TestBase;
import com.tn.qa.utils.Utilities;

import enums.RegistrationPageEnums;

public class RegisterTest extends TestBase {

	public RegisterTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();

	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication("Chrome");
		LandingPage landingpage = new LandingPage(driver);
		landingpage.openRegistrationPage();

	}

	@Test(priority = 1)
	public void verifyTnRegistrationWithValidUserNameAndValidPassword() {
		RegisterPage registerpage = new RegisterPage(driver);
		
		registerpage.registerWithCredentials(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"),
				Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("telephone"),
				dataprop.getProperty("validPassword"),dataprop.getProperty("validPassword"), true,true);
		registerpage.assertRegistrationDetails(
				RegistrationPageEnums.VALID_USERNAME_VALID_PASSWORD.credentialValidity.toString());
	}

	@Test(priority = 2)
	public void verifyTnRegistrationWithInvalidUserNameAndInvalidPassword() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.registerWithCredentials(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"),
				dataprop.getProperty("invalidEmail"), dataprop.getProperty("telephone"), dataprop.getProperty("invalidPassword"), dataprop.getProperty("invalidPassword"),
				false, true);
		registerpage.assertRegistrationDetails(
				RegistrationPageEnums.INVALID_USERNAME_INVALID_PASSWORD.credentialValidity.toString());
	}

	@Test(priority = 3)
	public void verifyTnReRegistrationWithValidUserNameAndValidPassword() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.registerWithCredentials(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"),
				dataprop.getProperty("email"), dataprop.getProperty("telephone"), dataprop.getProperty("validPassword"),dataprop.getProperty("validPassword"),true, true);
		registerpage.assertRegistrationDetails(
				RegistrationPageEnums.REREGISTER_VALID_USERNAME_VALID_PASSWORD.credentialValidity.toString());

	}

	@Test(priority = 4)
	public void verifyTnRegistrationWithValidUserNameAndInvalidConfirmPassword() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.registerWithCredentials(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"),
				dataprop.getProperty("email"), dataprop.getProperty("telephone"),
				dataprop.getProperty("validPassword"),dataprop.getProperty("invalidPassword"),true, true);
		registerpage.assertRegistrationDetails(
				RegistrationPageEnums.VALID_USERNAME_INVALID_CONF_PASSWORD.credentialValidity.toString());

	}

	@Test(priority = 5)
	public void verifyTnRegistrationWithEmptyFields() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.registerWithCredentials("", "", "", "", "","", false, true);
		registerpage.assertRegistrationDetails(
				RegistrationPageEnums.REGISTRATION_EMPTY_FIELDS.credentialValidity.toString());

	}

	@Test(priority = 6)
	public void verifyTnRegistrationWithEmptyFirstnameAndLastname() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.registerWithCredentials("", "", Utilities.generateEmailWithTimeStamp(),
				dataprop.getProperty("telephone"), dataprop.getProperty("validPassword"),dataprop.getProperty("validPassword"), false, true);
		registerpage.assertRegistrationDetails(
				RegistrationPageEnums.REG_EMPT_FNAME_LNAME_VALID_USER_PASS.credentialValidity.toString());

	}

	@Test(priority = 7)
	public void verifyTnRegistrationWithEmptyTelephoneAndEmail() {
		
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.registerWithCredentials(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"),
				"", "",
			dataprop.getProperty("validPassword"),dataprop.getProperty("validPassword"),true, true);
		registerpage.assertRegistrationDetails(
				RegistrationPageEnums.REG_EMPT_PHONE_EMPT_EMAIL.credentialValidity.toString());
	}

	@Test(priority = 8)
	public void verifyTnRegistrationWithoutClickingPrivacyPolicyCheckbox() {
		
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.registerWithCredentials(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"),
				Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("telephone"),
				dataprop.getProperty("validPassword"),dataprop.getProperty("validPassword"), true, false);
		registerpage.assertRegistrationDetails(
				RegistrationPageEnums.REG_WITHOUT_PVC_PLC_CHKBOX.credentialValidity.toString());

	}

	
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
