package com.App.Amazon.Pages;

import java.io.IOException;
import java.time.Duration;
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
import com.App.Amazon.Helpers.TestDataReader;
import com.google.common.base.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@Listeners(com.App.Amazon.Listeners.ExtentReportListener.class)
public class HomePage extends ObjectRepoReader {
	public static String NameAndDescSelected = null;
	public static String PriceSelected = null;
	public static String QuantitySelected = null;

	
	@SuppressWarnings("unchecked")
	public void SearchAndClickProduct(AppiumDriver<MobileElement> driver)throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		String product = TestDataReader.getValue("Product");
		@SuppressWarnings("unchecked")
		Wait waitObj = new FluentWait(driver)
				. withTimeout(60, TimeUnit.SECONDS)
				. pollingEvery(2, TimeUnit.SECONDS)
				. ignoring(Exception. class);
		
		waitObj.until(new Function<WebDriver, WebElement>() {
			  public WebElement apply(WebDriver driver) {
			    try {
					return driver.findElement(By.id(ObjectRepoReader.getObject("TextBox_Search")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			  }
			});;
				
		WebElement Ele = driver.findElement(By.id(ObjectRepoReader.getObject("TextBox_Search")));
		
		wait.until(ExpectedConditions.elementToBeClickable(Ele));
		driver.findElement(By.id(ObjectRepoReader.getObject("TextBox_Search"))).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id(ObjectRepoReader.getObject("TextBox_Search"))).sendKeys(product);
		Thread.sleep(4000);

		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(PointOption.point(219, 325)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(50)))
				.release().perform();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	public void SelectProduct(AppiumDriver<MobileElement> driver) throws IOException, InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement Ele = driver.findElement(By.id(ObjectRepoReader.getObject("TextBox_Search")));
		
		wait.until(ExpectedConditions.elementToBeClickable(Ele));
		MobileElement ScrollElement = driver.findElement(By.id(ObjectRepoReader.getObject("TextBox_Search")));
		int x = ScrollElement.getLocation().getX();
		int y = ScrollElement.getLocation().getY();
		int a = (x + 600);
		int b = (y + 950);
		System.out.println(x + "," + y + "," + a + "," + b);

		TouchAction touchAction = new TouchAction(driver);

		touchAction.press(PointOption.point(345, 1110)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
				.moveTo(PointOption.point(345, 340)).release().perform();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(ObjectRepoReader.getObject("List_Products"))));
		
		List<MobileElement> list1 = driver.findElements(By.id(ObjectRepoReader.getObject("List_Products")));

		int siz = list1.size();
		list1.get(siz - 1).click();

		NameAndDescSelected = driver
				.findElement(By.xpath(ObjectRepoReader.getObject("Element_ProductNameDesc_ItemPage"))).getText();

		PriceSelected = driver.findElement(By.xpath(ObjectRepoReader.getObject("Element_ProductPrice_ItemPage")))
				.getText();
		touchAction.press(PointOption.point(345, 1310)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
				.moveTo(PointOption.point(345, 340)).release().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjectRepoReader.getObject("Element_ProductQuantity_ItemPage"))));

		QuantitySelected = driver.findElement(By.xpath(ObjectRepoReader.getObject("Element_ProductQuantity_ItemPage")))
				.getAttribute("text");

	}

}