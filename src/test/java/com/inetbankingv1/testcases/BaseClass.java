package com.inetbankingv1.testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.testng.log4testng.Logger;
import org.testng.annotations.Parameters;

import com.inetbankingv1.utilities.ReadConfig;

import net.bytebuddy.implementation.FieldAccessor.PropertyConfigurable;

public class BaseClass {
	ReadConfig readConfig = new ReadConfig();
	
	public String baseURL = readConfig.getApplicationURL() ;
	public String userName =readConfig.getUserName() ;
	public String password =readConfig.getPassword();
	
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br) {

		logger = Logger.getLogger("iNetBanking");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromepath());
			driver= new ChromeDriver();
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxpath());
			driver= new  FirefoxDriver();
		}
		else if(br.equals("edge")) {
			System.out.print("Calling from edge browser");
			System.setProperty("webdriver.edge.driver", readConfig.getEdgepath());
			System.out.print("opened with edge browser");
			driver= new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
		
	}
	
	@AfterClass
	public void tearDown() {
		//driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname +".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot tken");
	}
			
	public String randomString() {
		String generatedString =RandomStringUtils.randomAlphabetic(8);
		return generatedString;
	}
	
	public static String RandomNum() {
		String gereratedNum = RandomStringUtils.randomNumeric(5);
		return gereratedNum;
	}
}
