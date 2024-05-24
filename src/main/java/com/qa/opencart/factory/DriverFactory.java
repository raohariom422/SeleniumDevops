package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager OptionsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop)
	{
		OptionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		String browserName =  prop.getProperty("browser").trim().toLowerCase();
		//String browserName = System.getProperty("browser");
		System.out.println("browser name is" + browserName);
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				// run on remote
				init_remoteDriver("chrome");
			}
			else
			{
				//local execution
				//driver = new ChromeDriver(OptionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
			}
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.chromedriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				// run on remote
				init_remoteDriver("firefox");
			}
			else
			{
			//driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
			}
		}
		else if(browserName.equalsIgnoreCase("safari"))
		{
			WebDriverManager.chromedriver().setup();
			
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.chromedriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				// run on remote
				init_remoteDriver("edge");
			}
			else
			{
			//driver = new EdgeDriver(OptionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(OptionsManager.getEdgeOptions()));
			}
		}
		else
		{
			System.out.println("Plz pass the right browser...." + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	/***
	 * get local copy of the driver due to thread safe
	 * @return 
	 */
	
	public synchronized static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	/**
	 * 
	 * @return
	 */
	public Properties initProp()
	{
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running Test cases on Env"+envName);
		try
		{
		if(envName == null)
		{
			System.out.println("no env passed....Running test cases on qa env");
			 ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
		}
		
		else
		{
			switch(envName.toLowerCase().trim())
			{
			case "qa":
				 ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			break;
			case "stage":
				 ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
			break;
			case "dev":
				 ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
			break;
			case "prod":
				 ip = new FileInputStream("./src/test/resources/config/config.properties");
			break;
			
			default:
				System.out.println("Wrong env is passed....no need to run the textcases");
				throw new FrameworkException("Wrong Env is passed here");
				//break;
			
			}
		}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getScreenshot()
	{
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+ System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
			//FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
		
	}
	private void init_remoteDriver(String browser)
	{
		System.out.println("Execution on Grid Server"+ browser);
		try
		{
		switch(browser.toLowerCase())
		{
		case "chrome":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), OptionsManager.getChromeOptions()));
			 
			break;
			
		case "firefox":
			
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), OptionsManager.getFirefoxOptions()));
			
			break;
			
		case "edge":
			
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), OptionsManager.getEdgeOptions()));
			 
			break;
			
			default:
				System.out.println("Please pass the right browser for remote execution...." + browser);
				throw new FrameworkException("NO REMOTE BROWSER EXCEPTION");
		}
		}
				
				 catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			
		
		
	}
	}


