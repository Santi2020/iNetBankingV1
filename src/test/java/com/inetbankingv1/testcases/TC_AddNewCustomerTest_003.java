package com.inetbankingv1.testcases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.inetbankingv1.pageobjects.AddCustomerPage;
import com.inetbankingv1.pageobjects.LoginPage;

public class TC_AddNewCustomerTest_003 extends BaseClass{

	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		//Create loginpage instance
		LoginPage lp= new LoginPage(driver);
		Thread.sleep(3000);
		lp.setUserName(userName);
		lp.setPassword(password);
		lp.clickSubmit();
		logger.info("AddNewCustomer-- Logged in successfully");
		Thread.sleep(3000);
		
		//Create AddCustomerPage instance
		AddCustomerPage addCustomer = new AddCustomerPage(driver);
		addCustomer.clickAddNewCustomer();
		logger.info("AddNewCustomer-- Clicked New Customer");
		Thread.sleep(5000);
		
		logger.info("AddNewCustomer-- Setting Customer details");
		addCustomer.SetCustName("Simba");
		addCustomer.setGender("male");
		addCustomer.setDOB("05", "15", "2022");
		Thread.sleep(2000);
		addCustomer.setAddress("My new Ddress");
		addCustomer.setCity("Fremont");
		addCustomer.setState("CA");
		addCustomer.setPin("123456");
		addCustomer.setMobile("22334455");
		
		String email = randomString()+"@gmail.com";
		addCustomer.setEmail(email);
		logger.info("AddNewCustomer-- Generated Random email");
		addCustomer.setPassword("simba");
		addCustomer.clickSubmit();
		
		boolean res = driver.getPageSource().contains("Customer Registered Successfully");
		try {
			if (res==true) {
				logger.info("AddNewCustomer--Test case passed");
				Assert.assertTrue(true);
			}
			else {
				logger.info("AddNewCustomer-- Test case failed");
				captureScreen(driver, "addNewCustomer");
				Assert.assertTrue(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
