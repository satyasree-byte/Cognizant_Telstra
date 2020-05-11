package com.App.Amazon.Pages;

import java.io.IOException;
import java.util.List;
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
import com.App.Amazon.Pages.HomePage;
import com.google.common.base.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

@Listeners(com.App.Amazon.Listeners.ExtentReportListener.class)
public class Validations extends ObjectRepoReader {

	public void VerifyProductDetails(AppiumDriver<MobileElement> driver) throws IOException {

		List<MobileElement> listNameAndDesc = driver
				.findElements(By.xpath(ObjectRepoReader.getObject("Element_ProductNameDesc_CheckoutPage")));

		int siz1 = listNameAndDesc.size();
		String NameAndDescAtCheckout = listNameAndDesc.get(siz1 - 2).getText();

		List<MobileElement> listPriceAtCheckout = driver
				.findElements(By.xpath(ObjectRepoReader.getObject(("Element_ProductPrice_CheckoutPage"))));

		int siz2 = listPriceAtCheckout.size();
		String PriceAtCheckout = listPriceAtCheckout.get(siz2 - 2).getAttribute("text");

		List<MobileElement> listQuantityAtCheckout = driver
				.findElements(By.xpath(ObjectRepoReader.getObject("Element_ProductQuantity_CheckoutPage")));

		int siz3 = listQuantityAtCheckout.size();
		String QuantityAtCheckout = listQuantityAtCheckout.get(siz3 - 1).getAttribute("text");

		// Validation of Name and Description as the name and decsription is at same

		if (HomePage.NameAndDescSelected.trim().contains(NameAndDescAtCheckout.split("|")[0].trim())
				&& (HomePage.PriceSelected.split(" ")[1].trim().equalsIgnoreCase(
						PriceAtCheckout.split("")[3] + PriceAtCheckout.split("")[4] + PriceAtCheckout.split("")[5]))
				&& (HomePage.QuantitySelected.equalsIgnoreCase(QuantityAtCheckout))) {

			System.out.println("Name and Description Validated successfully" + NameAndDescAtCheckout);
			System.out.println("Price is validated successfully" + PriceAtCheckout);
			System.out.println("Quantity is validated successfully" + QuantityAtCheckout);

			System.out.println("TEST CASE PASSED");
		}

	}

	public void VerifyLoginSucessful(AppiumDriver<MobileElement> driver) throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectRepoReader.getObject("Button_HamburgerMenu"))));
		
		if (driver.findElement(By.id(ObjectRepoReader.getObject("Button_HamburgerMenu"))).isDisplayed()) {
			System.out.println("Login Verified successfully");
		}
	}

	@SuppressWarnings("unchecked")
	public void VerifyLaunchSucessful(AppiumDriver<MobileElement> driver) throws IOException, InterruptedException {
		Wait waitObj = new FluentWait(driver)
				. withTimeout(60, TimeUnit.SECONDS)
				. pollingEvery(2, TimeUnit.SECONDS)
				. ignoring(Exception. class);
		
		waitObj.until(new Function<WebDriver, WebElement>() {
			  public WebElement apply(WebDriver driver) {
			    try {
					return driver.findElement(By.id(ObjectRepoReader.getObject("Button_SignIn")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			  }
			});
		
		if (driver.findElement(By.id(ObjectRepoReader.getObject("Button_SignIn"))).isDisplayed()) {
			System.out.println("App Launch Verified successfully");
		}

	}

	@SuppressWarnings("unchecked")
	public void VerifyCartPageIsNavigated(AppiumDriver<MobileElement> driver) throws IOException {
		Wait waitObj = new FluentWait(driver)
				. withTimeout(60, TimeUnit.SECONDS)
				. pollingEvery(2, TimeUnit.SECONDS)
				. ignoring(Exception. class);
		
		waitObj.until(new Function<WebDriver, WebElement>() {
			  public WebElement apply(WebDriver driver) {
			    try {
					return driver.findElement(By.xpath(ObjectRepoReader.getObject("Element_ProceedToBuy_CheckOutPage")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			  }
			});
		if (driver.findElement(By.xpath(ObjectRepoReader.getObject("Element_ProceedToBuy_CheckOutPage"))).isDisplayed()) {
			System.out.println("Navigation to cart Verified successfully");
		}

	}

}
