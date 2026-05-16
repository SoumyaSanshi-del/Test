package soumyasanshi.SeleniumFrameWorkDesign;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import soumyasanshi.BaseCommonTest.BaseTest;
import soumyasanshi.paeObjects.CartPage;
import soumyasanshi.paeObjects.CheckOutPage;
import soumyasanshi.paeObjects.ConfirmationPage;
import soumyasanshi.paeObjects.LandingPage;
import soumyasanshi.paeObjects.OrderPage;
import soumyasanshi.paeObjects.ProductCatalogePage;

public class StandAloneTest extends BaseTest {
	String itemName="ZARA COAT 3";

	@Test(dataProvider="getData",groups="PurchaseOrder")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String item=input.get("item").toString();
		ProductCatalogePage cat=landingPage.login(input.get("email").toString(), input.get("password").toString());
		
		cat.products(item);
		cat.addProdtoCart(item);
		CartPage cartPage=cat.cart();
		
		//In cartsection list of ietms
		Boolean match=cartPage.verifyProductDisplay(item);
		Assert.assertTrue(match);
		CheckOutPage checkout=cartPage.goToCheckout();
		checkout.selectCountry("India");
		ConfirmationPage cM=checkout.placeOrder();
		String message=cM.confirmmsg();
		try {
			Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Test(dependsOnMethods="submitOrder")
	public void testOrderProducts() {
		ProductCatalogePage cat=landingPage.login("pinky2000@gmail.com", "Pinky@#2000");
		OrderPage orderPage=cat.orders();
		Assert.assertTrue(orderPage.verifyOrderDisplay(itemName));
		
	}
	
	@Test
	public String Capture(String testCaseName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File sfile=ts.getScreenshotAs(OutputType.FILE);
		File desFile=new File(System.getProperty("user.dir")+"//reports// "+testCaseName+" .png");
		FileUtils.copyFile(sfile,desFile);
		return System.getProperty("user.dir")+"//reports// "+testCaseName+" .png";
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<Object,Object> map=new HashMap<Object,Object>();
//		map.put("email", "pinky2000@gmail.com");
//		map.put("password", "Pinky@#2000");
//		map.put("item", "ZARA COAT 3");
//		
//		HashMap<Object,Object> map2=new HashMap<Object,Object>();
//		map2.put("email", "sai01@gmail.com");
//		map2.put("password", "Sai@#2000");
//		map2.put("item", "ZARA COAT 3");
		List<HashMap<String,String>> list=getJsonDataTOFile();
		
		return new Object[][] {
			{list.get(0)},
			{list.get(1)},
	};
}
}
