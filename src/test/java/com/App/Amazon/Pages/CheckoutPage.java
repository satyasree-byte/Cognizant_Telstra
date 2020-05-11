package com.App.Amazon.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;

import com.App.Amazon.Helpers.ObjectRepoReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

@Listeners(com.App.Amazon.Listeners.ExtentReportListener.class)
public class CheckoutPage extends ObjectRepoReader  {

	
	public void clickOnAddToCart(AppiumDriver<MobileElement> driver) throws IOException, InterruptedException {
		
		driver.findElement(By.xpath(ObjectRepoReader.getObject("Button_AddToCart"))).click();	
		 
	}
	
	public void clickOnCartButton(AppiumDriver<MobileElement> driver) throws IOException {
		driver.findElement(By.xpath(ObjectRepoReader.getObject("Button_GoToCart"))).click();	
		
	}

}
