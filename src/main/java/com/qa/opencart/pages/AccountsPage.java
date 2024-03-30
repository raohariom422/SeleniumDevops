package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By accsHeaders = By.xpath("//div[@id='content']//h2");
//	private By myAccountLink = By.xpath("//h2[text()='My Account']");
//	private By   ss            = By.xpath("My Orders");
//	private By sm = By.xpath("My Affiliate Account");
//	private By hm = By.xpath("Newsletter");
	private By search = By.xpath("//input[@name='search']");
	
	
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	public String getAccPageTitle()
	{
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_TIME_OUT, AppConstants.Account_PAGE_TITLE_VALUE);
		System.out.println("Acc page title:" +title);
		return title;
	}
	
	public String getAccPageUrl()
	{
		String url = eleUtil.waitForURLConatinsAndFetch(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE);
		System.out.println("Acc Page Url" + url);
		return url;
	}
	
	public boolean isLogoutLinkExist()
	{
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_TIME_OUT).isDisplayed();
		
	}
	
	public boolean isSearchLinkExist()
	{
		return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_TIME_OUT).isDisplayed();
		
	}
	
	public List<String> getAccPageHeadersList()
	{
		List<WebElement> accHeadersList = eleUtil.waitForElementsVisible(AppConstants.DEFAULT_TIME_OUT, accsHeaders);
		List<String> accHeadersValList = new ArrayList<String>();
		for(WebElement e : accHeadersList)
		{
			String text  = e.getText();
			accHeadersValList.add(text);
		}
		return accHeadersValList;
	}
	

}
