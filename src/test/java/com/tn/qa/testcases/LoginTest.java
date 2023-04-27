package com.tn.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tn.qa.pages.LandingPage;
import com.tn.qa.pages.LoginPage;
import com.tn.qa.testbase.TestBase;
import com.tn.qa.utils.Utilities;

import enums.TestdataValidityEnum;

public class LoginTest extends TestBase {

	public LoginTest() throws Exception {
		super();
	}
	
	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();
	
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		LandingPage landingpage = new LandingPage(driver);
		landingpage.clickoOnMyAccontLink();
		landingpage.clickOnLoginAccountLink();
	}

	@Test(priority = 1)
	public void verifyLoginWithValidUserNameAndValidPassword()throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithUsernamePassword(prop.getProperty("validUsername"), prop.getProperty("validPassword"));
		loginPage.validateLogin(TestdataValidityEnum.VALID_USERNAME_VALID_PASSWORD.testdataValidity.toString());
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidUserNameAndInvalidPassword()throws Exception {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithUsernamePassword(Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("invalidPassword"));
		loginPage.validateLogin(TestdataValidityEnum.INVALID_USERNAME_INVALID_PASSWORD.testdataValidity.toString());
	}

	@Test(priority = 3)
	public void verifyLoginWithValidUserNameAndInvalidPassword()throws Exception {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithUsernamePassword(prop.getProperty("validUsername"), dataprop.getProperty("invalidPassword"));
		loginPage.validateLogin(TestdataValidityEnum.VALID_USERNAME_INVALID_PASSWORD.testdataValidity.toString());
	}

	@Test(priority = 4)
	public void verifyLoginWithInvalidUserNameAndValidPassword()throws Exception {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithUsernamePassword(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		loginPage.validateLogin(TestdataValidityEnum.INVALID_USERNAME_VALID_PASSWORD.testdataValidity.toString());
	}

	@Test(priority = 5)
	public void verifyLoginWithValidUserNameAndEmptyPasswordField()throws Exception {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithUsernamePassword(prop.getProperty("validUsername"), "");
		loginPage.validateLogin(TestdataValidityEnum.VALID_USERNAME_EMPTY_PASSWORD.testdataValidity.toString());
	}

	@Test(priority = 6)
	public void verifyLoginWithEmptyUserNameFieldAndValidPassword()throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithUsernamePassword("", prop.getProperty("validPassword"));
		loginPage.validateLogin(TestdataValidityEnum.EMPTY_USERNAME_VALID_PASSWORD.testdataValidity.toString());
	}

	@Test(priority = 7)
	public void verifyLoginWithEmptyUserNameFieldAndEmptyPasswordField()throws Exception {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithUsernamePassword("", "");
		loginPage.validateLogin(TestdataValidityEnum.EMPTY_USERNAME_EMPTY_PASSWORD.testdataValidity.toString());
	}

	
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}