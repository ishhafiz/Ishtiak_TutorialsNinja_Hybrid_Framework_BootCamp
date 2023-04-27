package com.tn.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	public WebDriver driver;
	
	@FindBy(linkText = "My Account")
	private WebElement myAccountLink;
	
	@FindBy(linkText = "Register")
	private WebElement registerAccountLink;
	
	@FindBy(linkText = "Login")
	private WebElement loginAccountLink;
	
	@FindBy(name = "search")
	private WebElement searchField;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickoOnMyAccontLink() {
		myAccountLink.click();
	}
	
	public void clickOnRegisterLink() {
		registerAccountLink.click();
	}
	
	public void clickOnLoginAccountLink() {
		loginAccountLink.click();
	}
	
	public void searchProducts(String keyword) {
		//searchField.click();
		driver.findElement(By.cssSelector("input.form-control.input-lg")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
	}
	
	public void openRegistrationPage() {
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
}
