package com.inetbankingv1.testcases;

import java.io.IOException;

import org.junit.Assert;
//import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.inetbankingv1.pageobjects.LoginPage;

public class TC_Login_001 extends BaseClass {

	@Test
	public void loginTest() throws InterruptedException, IOException {
		
		logger.info("Login-Loggin page");
		Thread.sleep(5000);
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(userName);
		logger.info("Login-Entered username");
		
		lp.setPassword(password);
		logger.info("Login-Entered password");
		
		lp.clickSubmit();
		System.out.println("Submit clicked");
		logger.info("Login-Clicked the submit");
		System.out.println(driver.getTitle());
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login-Login Test passed");
		}
		else {
			captureScreen(driver, "loginTest");
			System.out.println("Test case failed. Screenshot taken");
			Assert.assertTrue(false);
			logger.info("Login-Login Test failed");
		}
		
	}
}
