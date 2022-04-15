package com.inetbankingv1.testcases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbankingv1.pageobjects.LoginPage;
import com.inetbankingv1.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws IOException {
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(user);
		logger.info("'TC_LoginDDT_002'- Username provided");
		
		lp.setPassword(pwd);
		System.out.println("User = " + user +" Password = " + pwd);
		logger.info("'TC_LoginDDT_002'- Password provided");
		lp.clickSubmit();
		
		if (isAlertPresent()==true) {
			captureScreen(driver, "loginDDT");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("'TC_LoginDDT_002'- DDT Test failed");
		}
		else {
			Assert.assertTrue(true);
			logger.info("'TC_LoginDDT_002'- DDT Login passed");
			lp.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			System.out.println("Alert is present-- Invalid user");
			return true;
		}
		catch(NoAlertPresentException e) {
			System.out.println("Inside exception. Alert is not present-- Valid user");
			return false;
		}
	}
	
	@DataProvider(name ="LoginData")
	String[][] getData() throws IOException{
		String path = System.getProperty("user.dir")+ "/src/test/java/com/inetbankingv1/testdata/LoginData1.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		String logindata[][] = new String[rownum][colcount];
		for (int i =1; i<=rownum ; i++) {
			for(int j=0; j<colcount;j++) {
				logindata[i-1][j]= XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
	}
}
