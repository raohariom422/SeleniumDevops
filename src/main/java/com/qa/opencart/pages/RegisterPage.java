package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class RegisterPage {
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By registerSuccessMesg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	private WebDriver driver;
	private  ElementUtils eleUtil;
	
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public boolean resgisterUser(String firstName, String lastName, String email,String telephone, String password, String susbscribe)
	{
		eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_TIME_OUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);
		
		if(susbscribe.equalsIgnoreCase("yes"))
		{
			eleUtil.doclick(subscribeYes);
		}
		else
		{
			eleUtil.doclick(subscribeNo);
		}
		
		eleUtil.doActionsClick(agreeCheckBox);
		eleUtil.doclick(continueButton);
		String sucessMsg = eleUtil.waitForElementVisible(registerSuccessMesg, AppConstants.DEFAULT_TIME_OUT).getText();
		System.out.println("registration sucess msg"+ sucessMsg);
		if(sucessMsg.contains(AppConstants.USER_REG_SUCCESS_MESSG))
		{
			eleUtil.doclick(logoutLink);
			eleUtil.doclick(registerLink);
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
