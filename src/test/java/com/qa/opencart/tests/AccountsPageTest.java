package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetUp()
	{
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void accPageTitleTest()
	{
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.Account_PAGE_TITLE_VALUE);
		System.out.println("jenkins automatic build pipelines latest news ext");
		
	}
	
	@Test
	public void accPageURLTest()
	{
		String actURL = accPage.getAccPageUrl();
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE));
		
	}
	
	@Test
	public void isAccLinkExist()
	{
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchLinkExist()
	{
		Assert.assertTrue(accPage.isSearchLinkExist());
	}
	@Test
	public void headersList()
	{
		List<String> actualAccPageHeadersList = accPage.getAccPageHeadersList();
		System.out.println("Account Page headers lIST" +actualAccPageHeadersList);
		Assert.assertEquals(actualAccPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
		
		
	}
	@Test
	public void headersValeTest()
	{
		List<String> actualAccPageHeadersValueList = accPage.getAccPageHeadersList();
		System.out.println("Account Page headers lIST" +actualAccPageHeadersValueList);
		Assert.assertEquals(actualAccPageHeadersValueList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		
	}

}
