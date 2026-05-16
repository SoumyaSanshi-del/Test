package soumyasanshi.SeleniumFrameWorkDesign;


import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import soumyasanshi.BaseCommonTest.BaseTest;
import soumyasanshi.BaseCommonTest.Retry;
import soumyasanshi.paeObjects.CartPage;
import soumyasanshi.paeObjects.CheckOutPage;
import soumyasanshi.paeObjects.ConfirmationPage;
import soumyasanshi.paeObjects.LandingPage;
import soumyasanshi.paeObjects.ProductCatalogePage;

public class ErrorValidations extends BaseTest {

	@Test(retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String itemName="ZARA COAT 3";
		landingPage.login("pinky2000@gmail.com", "Pinky@2000");
		landingPage.getErrorMessage();
//		Assert.assertEquals("",landingPage.getErrorMessage());
		Assert.assertEquals("Incorrect mail or password.", landingPage.getErrorMessage());

	}
	
	@Test(groups="ErrorHandling")
	public void ProductErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String itemName="ZARA COAT 3";
		ProductCatalogePage cat=landingPage.login("sai01@gmail.com", "Sai@#2000");
		
		cat.products(itemName);
		cat.addProdtoCart(itemName);
		CartPage cartPage=cat.cart();
		
		Boolean match=cartPage.verifyProductDisplay(itemName+"33");
		Assert.assertFalse(match);


	}


}
