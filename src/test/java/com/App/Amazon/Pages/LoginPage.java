package com.App.Amazon.Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import com.App.Amazon.Helpers.ObjectRepoReader;
import com.App.Amazon.Helpers.TestDataReader;
import com.google.common.base.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/*
All the required elements for the login page 
Fetching elements from the OR - properties file
*/
@Listeners(com.App.Amazon.Listeners.ExtentReportListener.class)
public class LoginPage extends ObjectRepoReader {

	public void clickOnSignin(AppiumDriver<MobileElement> driver) throws InterruptedException, IOException {
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectRepoReader.getObject("Button_SignIn"))));
		
		driver.findElement(By.id(ObjectRepoReader.getObject("Button_SignIn"))).click();
	}

	@SuppressWarnings("unchecked")
	public void EnterEmailOrPhone(AppiumDriver<MobileElement> driver) throws Exception {
		
		String phone = TestDataReader.getValue("PhoneNumber");
		
		 Wait waitObj = new FluentWait(driver)
					. withTimeout(2, TimeUnit.MINUTES)
					. pollingEvery(1, TimeUnit.SECONDS)
					. ignoring(Exception. class);
			
			waitObj.until(new Function<WebDriver, WebElement>() {
				  public WebElement apply(WebDriver driver) {
				    try {
						return driver.findElement(By.xpath(ObjectRepoReader.getObject("TextBox_Email")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				  }
				});
		driver.findElement(By.xpath(ObjectRepoReader.getObject("TextBox_Email"))).click();
	
		//wait.until(ExpectedConditions.el(By.xpath(ObjectRepoReader.getObject("TextBox_Email"))));
		driver.findElement(By.xpath(ObjectRepoReader.getObject("TextBox_Email"))).setValue(phone);
	}

	@SuppressWarnings("unchecked")
	public void EnterPassword(AppiumDriver<MobileElement> driver) throws Exception {
		
		 String password = TestDataReader.getValue("Password");
		 
		 Wait waitObj = new FluentWait(driver)
					. withTimeout(2, TimeUnit.MINUTES)
					. pollingEvery(1, TimeUnit.SECONDS)
					. ignoring(Exception. class);
			
			waitObj.until(new Function<WebDriver, WebElement>() {
				  public WebElement apply(WebDriver driver) {
				    try {
						return driver.findElement(By.xpath(ObjectRepoReader.getObject("TextBox_Password")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				  }
				});
		driver.findElement(By.xpath(ObjectRepoReader.getObject("TextBox_Password"))).sendKeys(password);

	}

	public void clickOnContinue(AppiumDriver<MobileElement> driver) throws IOException, InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjectRepoReader.getObject("Button_Continue"))));
		
		driver.findElement(By.xpath(ObjectRepoReader.getObject("Button_Continue"))).click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
	}

	public void clickOnLogin(AppiumDriver<MobileElement> driver) throws IOException {
		driver.findElement(By.xpath(ObjectRepoReader.getObject("Button_Login"))).click();

	}

}
