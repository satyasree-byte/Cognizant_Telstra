package com.App.Amazon.Base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;


import com.App.Amazon.Helpers.TestDataReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@Listeners(com.App.Amazon.Listeners.ScreenshotListener.class)
public class BaseDriver {

	public static AppiumDriver<MobileElement> driver;

	@BeforeTest
	public void Base() {

		try {
			Reporter.log("================Browser Session Started===============",true);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("no", true);
			capabilities.setCapability("newCommandTimeout", 100000);
			capabilities.setCapability("no-reset", "true");
			capabilities.setCapability("full-reset", "false");
			
			// device details
			capabilities.setCapability("deviceName", "ZF6224FQ74");
			capabilities.setCapability("UDID", "ZF6224FQ74");
			capabilities.setCapability("platformVersion", "10");
			capabilities.setCapability("platformName", "Android");
			
			// Application details
			String apkpath = "C:\\Users\\satyasree\\Downloads\\Amazon_shopping.apk";
			File app = new File(apkpath);
			capabilities.setCapability("app", app.getAbsolutePath());

			capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
			capabilities.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
			
			URL url = new URL(TestDataReader.getValue("BasePortURL"));
			driver = new AndroidDriver<MobileElement>(url, capabilities);
			Reporter.log("===============Application Started================",true);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			

		} catch (Exception e) {
			System.out.println("Cause is" + e.getCause());
			System.out.println("Message is" + e.getMessage());
			e.printStackTrace();
		}

	}

	@AfterTest
	public void closeDriver() {
		driver.close();
		Reporter.log("================Application Ended=====================", true);

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
		Reporter.log("==================Browser Session End=================", true);

	}

// Works as per listener and takes screenshots when failed.
	public void failed(String TestName) {

		try {
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
			FileUtils.copyFile(srcfile, new File("C:\\Users\\satyasree\\Cognizant_Telstra\\src\\test\\Screenshots\\"
					+TestName+"_"+"test_failure.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
