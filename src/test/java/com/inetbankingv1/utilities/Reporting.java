package com.inetbankingv1.utilities;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentLogger;
	
	
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); 
		String repName ="San_Test-Report"+timeStamp+".html";
		htmlReporter = new ExtentHtmlReporter (System.getProperty("user.dir")+"/test-output/"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		System.out.println("RepName =" +repName);
		System.out.println("here Report location = " + System.getProperty("user.dir")+"/test-output/"+repName);
		//System.out.println("here Report location = " + System.getProperty("user.dir")+"/test-output/"+ repName);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Simba");
		
		htmlReporter.config().setDocumentTitle("INetBanking project");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr) {
		System.out.println("Inside onTestSuccess");
		extentLogger= extent.createTest(tr.getName());
		extentLogger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN ));
		System.out.println("Created the report onTestSuccess");
	}
	
	public void onTestFailure(ITestResult tr) {
		extentLogger= extent.createTest(tr.getName());
		extentLogger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(),ExtentColor.RED ));
		
		String screenshotPath= System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		File f = new File(screenshotPath);
		if (f.exists()) {
			try {
				extentLogger.fail("Srreenshot is below:"+ extentLogger.addScreenCaptureFromPath(screenshotPath) );
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void onTestSkipped(ITestResult tr) {
		extentLogger= extent.createTest(tr.getName());
		extentLogger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE ));
	}
	
	public void onFinish(ITestContext testContext) {
		System.out.println("Inside onFinish");
		extent.flush();
	}
}
