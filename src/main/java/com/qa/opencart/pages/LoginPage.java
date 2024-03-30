package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	//private bY lOCATORS
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	
	//Constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	
	@Step("getting the login page title")
	public String getLoginPageTitle()
	{
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login Page Title" +title);
		return title;
	}
	@Step("getting the url page title")
	public String getLoginPageURL()
	{
		String url = eleUtil.waitForURLConatinsAndFetch(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login page url is" + url);
		return url;
	}
	@Step("checking  the forgot password link")
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.DEFAULT_TIME_OUT).isDisplayed();
		
	}
	
	//encapsulation-> private variable used in public methods
	@Step("login with username : {0} and password : {1}")
	public AccountsPage doLogin(String un,String pwd)
	{
		eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doclick(loginBtn);
		return new AccountsPage(driver);
	}
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doclick(registerLink);
		return new RegisterPage(driver);
	}

}
