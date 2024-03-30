package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtils {
	
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	WebDriverWait wait;
	
	
	public ElementUtils(WebDriver driver)
	{
		this.driver = driver;
		
		jsUtil = new JavaScriptUtil(driver);
		
	}
	
	
	public WebElement getElement(By locator)
	{
		WebElement element = null;
		try
		{
			element = driver.findElement(locator);
			if(Boolean.parseBoolean(DriverFactory.highlight))
					{
				      jsUtil.flash(element);
					}
					
			
			
					
			
			
		}
		catch(Exception e)
		{
			System.out.println("some exception got occured while creating the webelement.......");
		}
		return element;
	}
	
	
	
	public void doclick(By locator)
	{
		try
		{
			getElement(locator).click();
		}
		catch(Exception e)
		{
			System.out.println("some exception got occured while clicking on the webelement.......");
		}
	}
	
	public void doSendKeys(By locator,String value)
	{
		try
		{
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
		}
		
			catch(Exception e) 
			{
				e.printStackTrace();
				e.getMessage();
				System.out.println("some exception got occured while entering value in field .......");
			}
		
	}
	
	public String doGetTitle()
	{
		try
		{
			return driver.getTitle();
		}
		catch(Exception e)
		{
			System.out.println("some exception got occured while getting title....");
		}
		return null;
	}
	
	public String waitForTitlePresent(String titlevalue,long timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleIs(titlevalue));
		return driver.getTitle();
		
	}
	
	public void doActionsClick(By locator)
	{
		Actions action = new Actions(driver);
		action.click(getElement(locator)).build().perform();
	}
	public String doGetText(By locator)
	{
		return getElement(locator).getText();
	}
	public boolean doElementIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	public List<WebElement> getElements(By locator)
	{
		
		return driver.findElements(locator);
		
	}
	
	public void getElementAttributes(By locator, String attrName)
	{
		List<WebElement> eleList = getElements(locator);
		for(WebElement e : eleList)
		{
			String attrVal = e.getAttribute(attrName);
		    System.out.println(attrVal);
		}
		
		
	}
	public String getElementAttribute(By locator,String attrName)
	{
		return getElement(locator).getAttribute(attrName);
	}
	
	public int getTotalElementsCount(By locator)
	{
		int eleCount =  getElements(locator).size();
		System.out.println("total elements for :" + locator + "---->" + eleCount);
		return eleCount;
	}
	
	public ArrayList<String> getElementsTextList(By locator)
	{
		ArrayList<String> eleTextList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for(WebElement el : eleList)
		{
			String text = el.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}
	
	public void doSelectDropdownByIndex(By locator,int index)
	{
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
		
	}
	public void doSelectDropDownByValue(By locator,String value)
	{
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	public void doSelectDropDownByVisibleText(By locator,String text)
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public List<WebElement> getTotalDropDownOptionsList(By locator)
	{
		Select select = new Select(getElement(locator));
		return select.getOptions();
	}
	public int getTotalDropDownOptions(By locator)
	{
		
		int optionsCount = getTotalDropDownOptionsList(locator).size();
		System.out.println("options count is --" + optionsCount);
		return optionsCount;
	}
	public ArrayList<String> getDropDownOptionsTextList(By locator)
	{
		ArrayList <String> optionsTextList = new ArrayList<String>();
		List<WebElement> optionsList = getTotalDropDownOptionsList(locator);
		for(WebElement e :optionsList)
		{
			String text = e.getText();
			optionsTextList.add(text);
		}
		return optionsTextList;
	}
	public void selectDropDownValue(By locator,String expValue)
	{
		List<WebElement> optionsList = getTotalDropDownOptionsList(locator);
		for(WebElement e: optionsList)
		{
			String text = e.getText();
			System.out.println(text);
			if(text.equals(expValue))
			{
		      e.click();
		      break;
			}
		}
	}
	public void clickOnSpecificLink(By locator, String value)
	{
		List<WebElement> langLinks = getElements(locator);
		for(WebElement e : langLinks)
		{
			String text = e.getText();
			System.out.println(text);
			if(text.equals(value))
			{
				e.click();
				break;
			}
		}
	}
	
	public ArrayList<String> returnLangList(By locator)
	{
		ArrayList<String> addLinks = new ArrayList<String>();
		List<WebElement> langLinks = getElements(locator);
		for(WebElement e :langLinks)
		{
			String text = e.getText();
			addLinks.add(text);
		}
		return addLinks;
	}
	public void doSearch(By suggestionList, String suggName)
	{
		List<WebElement> suggList = getElements(suggestionList);
		System.out.println(suggList.size());
		for(WebElement e : suggList)
		{
			String text = e.getText();
			System.out.println(text);
			if(text.contains(suggName))
			{
				e.click();
				break;
			}
		}
	}
	
	public WebElement waitForElementVisible(By locator,int timeOut)
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public String waitForTitleContainsAndFetch(int timeout,String titleFractionValue)
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleContains(titleFractionValue));
		return driver.getTitle();
	}
	
	public String waitForURLConatinsAndFetch(int timeout,String urlFractionValue)
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(urlFractionValue));
		return driver.getCurrentUrl();
	}
	
	public List<WebElement> waitForElementsVisible(int timeout,By locator)
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

}
