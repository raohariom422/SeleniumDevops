package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//Inheritance
@Epic("EPIC : 100: design login for open cart app")
@Story("US-LOGIN : 101 design login page features for open cart")
public class LoginPageTest extends BaseTest{
	
	
	@Severity(SeverityLevel.MINOR)
	@Description("title of the page.....Author->Hariom")
	@Test(priority=1)
	public void loginPageTitleTest() throws InterruptedException
	{
		Thread.sleep(3000);
		String actualTitle = loginPage.getLoginPageTitle();
		String expectedTitle = AppConstants.LOGIN_PAGE_TITLE_VALUE;
		Assert.assertEquals(actualTitle, expectedTitle);		
		
	}
	@Severity(SeverityLevel.NORMAL)
	@Description("url of the page.....Author->Hariom")
	@Test(priority=2)
	public void loginPageUrlTest() throws InterruptedException
	{
		Thread.sleep(3000);
		String actualTitle = loginPage.getLoginPageURL();
		Assert.assertTrue(actualTitle.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	@Severity(SeverityLevel.BLOCKER)
	@Description("checking frorgot password link exist.....Author->Hariom")
	@Test(priority=3)
	public void fogotPwdLinkText() throws InterruptedException
	{
		Thread.sleep(3000);
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority=4)
	public void loginTest() throws InterruptedException
	{
		Thread.sleep(3000);
		accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
		
		
	}

}
