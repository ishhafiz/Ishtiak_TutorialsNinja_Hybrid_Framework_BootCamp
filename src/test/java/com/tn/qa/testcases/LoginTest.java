package com.tn.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tn.qa.testbase.TestBase;
import com.tn.qa.utils.Utilities;

public class LoginTest extends TestBase {

	public LoginTest() throws Exception {
		super();
	}
	
	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));

		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@Test(priority = 1)
	public void verifyingLoginWithValidUserNameAndValidPassword()throws Exception {

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validUsername"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		softassert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		softassert.assertAll();

	}

	@Test(priority = 2)
	public void verifyingLoginWithInvalidUserNameAndInvalidPassword()throws Exception {

		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]"))
				.getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCredentialMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warrning message is not correct");
		softassert.assertAll();

	}

	@Test(priority = 3)
	public void verifyingLoginWithValidUserNameAndInvalidPassword()throws Exception {

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validUsername"));
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible"))
				.getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCredentialMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warrning message is not correct");
		softassert.assertAll();

	}

	@Test(priority = 4)
	public void verifyingLoginWithInvalidUserNameAndValidPassword()throws Exception {

		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible"))
				.getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCredentialMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warrning message is not correct");
		softassert.assertAll();

	}

	@Test(priority = 5)
	public void verifyingLoginWithValidUserNameAndEmptyPassword()throws Exception {

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validUsername"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible"))
				.getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCredentialMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warrning message is not correct");
		softassert.assertAll();

	}

	@Test(priority = 6)
	public void verifyingLoginWithEmptyUserNameAndValidPassword()throws Exception {

		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible"))
				.getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCredentialMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warrning message is not correct");
		softassert.assertAll();

	}

	@Test(priority = 7)
	public void verifyingLoginWithEmptyUserNameAndEmptyPassword()throws Exception {

		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible"))
				.getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCredentialMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warrning message is not correct");
		softassert.assertAll();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}