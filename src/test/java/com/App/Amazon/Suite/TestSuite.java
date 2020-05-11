package com.App.Amazon.Suite;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.App.Amazon.Base.BaseDriver;
import com.App.Amazon.Helpers.TestDataReader;
import com.App.Amazon.Pages.CheckoutPage;
import com.App.Amazon.Pages.HomePage;
import com.App.Amazon.Pages.LoginPage;
import com.App.Amazon.Pages.Validations;

@Listeners(com.App.Amazon.Listeners.ExtentReportListener.class)
public class TestSuite extends BaseDriver {

	HomePage Home = new HomePage();
	LoginPage Login = new LoginPage();
	CheckoutPage Checkout = new CheckoutPage();
	Validations Validate = new Validations();

	@Test(enabled = true)
	public void SearchFunctionality() throws Exception {
		
		// Validating if the launch is successful and page is loaded
		Validate.VerifyLaunchSucessful(driver);

		// Login process
		Login.clickOnSignin(driver);
		Login.EnterEmailOrPhone(driver);
		Login.clickOnContinue(driver);
		Login.EnterPassword(driver);
		Login.clickOnLogin(driver);

		// Validating if the login is sucessful and user landed on home page
		Validate.VerifyLoginSucessful(driver);

		// Selecting the required product and Purchasing it.
		Home.SearchAndClickProduct(driver);
		Home.SelectProduct(driver);
		Checkout.clickOnAddToCart(driver);
		Checkout.clickOnCartButton(driver);

		Validate.VerifyCartPageIsNavigated(driver);
		
		// Validating product details like Name , Price and Description
		Validate.VerifyProductDetails(driver);

	}

}
