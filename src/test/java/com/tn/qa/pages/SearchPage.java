package com.tn.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class SearchPage {

	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input.form-control.input-lg")
	private WebElement searchField;
	
	@FindBy(css = "button.btn.btn-default.btn-lg")
	private WebElement searchButton;
	
	@FindBy(css = "#content p:nth-child(7)")
	private WebElement noProdMsg;
	
	public void inputKeyword(String keyword) {
		searchField.sendKeys(keyword);
	}
	
	public void clickSearchButton() {
		searchButton.click();;
	}
	
	public String getNoProdMsg() {
		return noProdMsg.getText();
	}
	
	public void assertSearchResultsWithValidProducts(String keyword) {
		String actualSearchText = driver
				.findElement(By.xpath("//h1[contains(text(), \"Search - " + keyword + "\")]")).getText();
		String expectedSearchText = "Search - " + keyword;
		softassert.assertEquals(actualSearchText, expectedSearchText, "Search Text does not match");
		List<WebElement> productTitleCount = driver
				.findElements(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/h4/a"));
		System.out.println("Number of products: " + productTitleCount.size());
		for (int i = 1; i <= productTitleCount.size(); i++) {
			String productTitle = productTitleCount.get(i - 1).getText();
			System.out.println("Product Name: " + productTitle);
			WebElement productDesc = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[1]"));
			WebElement productPrice = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[2]"));
			String[] priceAndTaxStr = productPrice.getText().split("Ex");

			System.out.println("Product Description: " + productDesc.getText());
			System.out.println("Product Price: " + priceAndTaxStr[0]);
			System.out.println("Product Tax: " + priceAndTaxStr[1].replace("Tax:", ""));

			WebElement prodImage = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[1]/a/img"));

			WebElement addToCart = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[1]"));
			WebElement wishList = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[2]"));
			WebElement compareButton = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[3]"));

			softassert.assertTrue(compareButton.isDisplayed());
			softassert.assertTrue(wishList.isDisplayed());
			softassert.assertTrue(addToCart.isDisplayed());
			softassert.assertTrue(prodImage.isDisplayed());

			softassert.assertTrue((productTitle.toLowerCase()).contains(keyword.toLowerCase()));
			// Validating price is not 0
			softassert.assertTrue(Float.parseFloat((priceAndTaxStr[0].replace("$", "").replace(",", ""))) > 0,
					"message");
			// Validating tax is not 0
			softassert.assertTrue(
					Float.parseFloat((priceAndTaxStr[1].replace("Tax:", "").replace("$", "").replace(",", ""))) > 0,
					"message");

			softassert.assertAll();
		}
	}
	
	public void assertSearchResultsWithInvalidProductName(String keyword) {
		inputKeyword(keyword);
		clickSearchButton();
		
		String actualInvalidSearchText = driver
				.findElement(By.xpath("//h1[contains(text(), \"Search - " + keyword + "\")]")).getText();
		String expectedInvalidSearchText = "Search - " + keyword;
		softassert.assertTrue(actualInvalidSearchText.contains(expectedInvalidSearchText), "Invalid Search Text does not match");

		String actualProductUnavailabiltyText = getNoProdMsg();
		String expectedProductUnavailabiltyText = "There is no product that matches the search criteria.";
		softassert.assertEquals(actualProductUnavailabiltyText, expectedProductUnavailabiltyText,
				"Product unavailability text does not exists.");
		softassert.assertAll();
	}
	
	public void assertSearchIconFunction() {
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		
		String actualProductUnavailabiltyText = getNoProdMsg();
		String expectedProductUnavailabiltyText = "There is no product that matches the search criteria.";
		softassert.assertEquals(actualProductUnavailabiltyText, expectedProductUnavailabiltyText,
				"Product unavailability text does not exists.");
		softassert.assertAll();

	}
	
	public void assertAllProducts(String keyword) {
		inputKeyword(keyword);
		clickSearchButton();

		List<WebElement> productTitleCount = driver
				.findElements(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/h4/a"));
		System.out.println("Number of products: " + productTitleCount.size());
		for (int i = 1; i <= productTitleCount.size(); i++) {
			String productTitle = productTitleCount.get(i - 1).getText();
			System.out.println("Product Name: " + productTitle);
			WebElement productDesc = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[1]"));
			WebElement productPrice = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[1]/p[2]"));
			String[] priceAndTaxStr = productPrice.getText().split("Ex");

			System.out.println("Product Description: " + productDesc.getText());
			System.out.println("Product Price: " + priceAndTaxStr[0]);
			System.out.println("Product Tax: " + priceAndTaxStr[1].replace("Tax:", ""));

			WebElement prodImage = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[1]/a/img"));

			WebElement addToCart = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[1]"));
			WebElement wishList = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[2]"));
			WebElement compareButton = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[" + i + "]/div/div[2]/div[2]/button[3]"));

			softassert.assertTrue(compareButton.isDisplayed());
			softassert.assertTrue(wishList.isDisplayed());
			softassert.assertTrue(addToCart.isDisplayed());
			softassert.assertTrue(prodImage.isDisplayed());

			softassert.assertAll();
		}
	}
	
}
