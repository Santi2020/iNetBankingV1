package com.inetbankingv1.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	WebDriver lDriver;
	public AddCustomerPage(WebDriver rDriver){
		lDriver=rDriver;
		PageFactory.initElements(rDriver, this);
	}
	
	@FindBy(how =How.XPATH, using ="/html/body/div[3]/div/ul/li[2]/a")
	WebElement lnkAddNewCustomer;
	
	@FindBy(how =How.NAME, using ="name")
	WebElement txtCustomerName;
	
	@FindBy(how =How.NAME, using ="rad1")
	WebElement rdGender;
	
	@FindBy(how =How.NAME, using ="dob")
	WebElement txtdob;
	
	@FindBy(how =How.NAME, using ="addr")
	WebElement txtaddr;
	
	@FindBy(how =How.NAME, using ="city")
	WebElement txtcity;
	
	@FindBy(how =How.NAME, using ="state")
	WebElement txtstate;
	
	@FindBy(how =How.NAME, using ="pinno")
	WebElement txtpinno;
	
	@FindBy(how =How.NAME, using ="telephoneno")
	WebElement txtMobile;
	
	@FindBy(how =How.NAME, using ="emailid")
	WebElement txtemailid;
	
	@FindBy(how =How.NAME, using ="password")
	WebElement txtpassword;
	
	@FindBy(how =How.NAME, using ="sub")
	WebElement btnSubmit;
	
	@FindBy(how =How.NAME, using ="res")
	WebElement btnReset;
	
	
	public void clickAddNewCustomer() {
		lnkAddNewCustomer.click();
	}
	
	public void SetCustName(String cName) {
		txtCustomerName.sendKeys(cName);
	}
	
	public void setGender(String cGender) {
		rdGender.click();;
	}
	
	public void setDOB(String mm, String dd, String yy) {
		txtdob.sendKeys(mm);
		txtdob.sendKeys(dd);
		txtdob.sendKeys(yy);
	}
	
	public void setAddress(String cAddress) {
		txtaddr.sendKeys(cAddress);
	}
	
	public void setCity(String cCity) {
		txtcity.sendKeys(cCity);
	}
	public void setState(String cState) {
		txtstate.sendKeys(cState);
	}

	
	public void setPin(String cPin) {
		txtpinno.sendKeys(String.valueOf(cPin));
	}
	
	public void setMobile(String cMobile) {
		txtMobile.sendKeys(cMobile);
	}
	
	public void setEmail(String cEmail) {
		txtemailid.sendKeys(cEmail);
	}
	
	public void setPassword(String cPassword) {
		txtpassword.sendKeys(cPassword);
	}
	
	public void clickSubmit() {
		btnSubmit.click();;
	}
}
