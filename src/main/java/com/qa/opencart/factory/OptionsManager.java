package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop)
	{
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions()
	{
		co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		
		if(Boolean.parseBoolean(prop.getProperty("remote")))
		{
			co.setBrowserVersion(prop.getProperty("browserversion"));
			co.setCapability("browserName", "chrome");
			co.setCapability("enableVNC", true);
			co.setCapability("name", prop.getProperty("testcasename"));
		}
		
		if(prop.getProperty("headless").trim().equalsIgnoreCase("true"))
		{
			System.out.println("Running Chrome in Headless mode");
			co.addArguments("--headless");
		}
		if(prop.getProperty("incognito").trim().equalsIgnoreCase("true"))
		{
			co.addArguments("--incognito");
		}
		
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions()
	{
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("remote")))
		{
			
			fo.setBrowserVersion(prop.getProperty("browserversion"));
			fo.setCapability("browserName", "firefox");
			fo.setCapability("enableVNC", true);
			fo.setCapability("name", prop.getProperty("testcasename"));
			fo.setCapability("marionette", true);
			fo.addPreference("devtools.debugger.remote-enabled", true);
			
		}
		if(prop.getProperty("headless").trim().equalsIgnoreCase("true"))
		{
			fo.addArguments("--headless");
		}
		if(prop.getProperty("incognito").trim().equalsIgnoreCase("true"))
		{
			fo.addArguments("--incognito");
		}
		
		return fo;
	}
	public EdgeOptions getEdgeOptions()
	{
		eo = new EdgeOptions();
		if(prop.getProperty("headless").trim().equalsIgnoreCase("true"))
		{
			eo.addArguments("--headless");
		}
		if(prop.getProperty("incognito").trim().equalsIgnoreCase("true"))
		{
			eo.addArguments("--incognito");
		}
		
		return eo;
	}
}
