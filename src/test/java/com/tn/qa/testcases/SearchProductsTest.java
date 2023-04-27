package com.tn.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tn.qa.pages.LandingPage;
import com.tn.qa.pages.SearchPage;
import com.tn.qa.testbase.TestBase;

public class SearchProductsTest extends TestBase {

	public SearchProductsTest() throws Exception {
		super();
	}

	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));

	}

	@Test(priority = 1)
	public void verifySearchUsingValidProductName() {
		String searchText = "mac";
		LandingPage landingpage = new LandingPage(driver);
		SearchPage searchPage = new SearchPage(driver);
		landingpage.searchProducts(searchText);
		searchPage.assertSearchResultsWithValidProducts(searchText);
	}

	@Test(priority = 2)
	public void verifySearchFieldUsingInvalidProductName() {
		String invalidSearchText = "apple watch";
		LandingPage landingpage = new LandingPage(driver);
		SearchPage searchPage = new SearchPage(driver);
		landingpage.searchProducts(invalidSearchText);
		searchPage.assertSearchResultsWithInvalidProductName(invalidSearchText);
	}

	@Test(priority = 3)
	public void verifySearchIconFunctionWithEmptyField() {
		LandingPage landingpage = new LandingPage(driver);
		SearchPage searchPage = new SearchPage(driver);
		landingpage.searchProducts("");
		searchPage.assertSearchIconFunction();
	}

	@Test(priority = 4)
	public void verifySearchingBlankspaceReturnsAllProducts() {
		String searchText = "  ";
		LandingPage landingpage = new LandingPage(driver);
		SearchPage searchPage = new SearchPage(driver);
		landingpage.searchProducts(searchText);
		searchPage.assertAllProducts(searchText);
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
