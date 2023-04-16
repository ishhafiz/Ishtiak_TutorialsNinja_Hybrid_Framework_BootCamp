package com.tn.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tn.qa.testbase.TestBase;
import com.tn.qa.utils.Utilities;

public class RegisterTest extends TestBase {

	public RegisterTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication("Chrome");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@Test(priority = 1)
	public void verifyTnRegistrationWithValidUserNameAndValidPassword() {
		driver.findElement(By.id("input-firstname")).sendKeys("Ishtiak");
		driver.findElement(By.id("input-lastname")).sendKeys("Hafiz");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("267-770-4693");
		driver.findElement(By.id("input-password")).sendKeys("I$hninja1207");
		driver.findElement(By.id("input-confirm")).sendKeys("I$hninja1207");
		boolean contBtnDisplayed = driver.findElement(By.cssSelector("input.btn.btn-primary")).isDisplayed();
		softassert.assertEquals(contBtnDisplayed, true, "Continue button is not displayed");
		driver.findElement(By.xpath("//label[@class='radio-inline'][2]/input")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		softassert.assertAll();
		System.out.println("Successfully validated registration with valid credentials.");
	}

	@Test(priority = 2)
	public void verifyTnRegistrationWithInvalidUserNameAndInvalidPassword() {
		driver.findElement(By.id("input-firstname")).sendKeys("Ishtiak");
		driver.findElement(By.id("input-lastname")).sendKeys("Hafiz");
		driver.findElement(By.id("input-email")).sendKeys("abc@123");
		driver.findElement(By.id("input-telephone")).sendKeys("267-770-4693");
		driver.findElement(By.id("input-password")).sendKeys("123");
		driver.findElement(By.id("input-confirm")).sendKeys("123");
		driver.findElement(By.xpath("//label[@class='radio-inline'][2]/input")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String actualEmailWarningMessage = driver
				.findElement(By.xpath("//input[@id='input-email']//following-sibling::div")).getText();
		String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
		softassert.assertEquals(actualEmailWarningMessage, expectedEmailWarningMessage);
		String actualPasswordWarningMessage = driver
				.findElement(By.xpath("//input[@id='input-password']//following-sibling::div")).getText();
		String expectePassworddWarningMessage = "Password must be between 4 and 20 characters!";
		softassert.assertEquals(actualPasswordWarningMessage, expectePassworddWarningMessage);
		softassert.assertAll();
		System.out.println("Successfully validated registration with invalid credentials.");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
