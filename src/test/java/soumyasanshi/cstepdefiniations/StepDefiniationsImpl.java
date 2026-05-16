package soumyasanshi.cstepdefiniations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import soumyasanshi.BaseCommonTest.BaseTest;
import soumyasanshi.paeObjects.CartPage;
import soumyasanshi.paeObjects.CheckOutPage;
import soumyasanshi.paeObjects.ConfirmationPage;
import soumyasanshi.paeObjects.LandingPage;
import soumyasanshi.paeObjects.ProductCatalogePage;

public class StepDefiniationsImpl extends BaseTest {
	public  LandingPage landingPage;
	public ProductCatalogePage cat;
	public CartPage cartPage;
	public CheckOutPage checkout;
	public ConfirmationPage cM;
	
	@Given("Landed on Ecommerce Page")
	public void Landed_On_Ecomerce_Page() throws IOException {
		landingPage=lauchApplication();
	}
	
	@Given("^Logged with username (.+) and (.+)$")
	public void Logged_With_User_Name(String name,String password) {
		cat=landingPage.login(name, password);
	}
	
	@When("^Add the (.+) from cart$")
	public void Add_Product_Name_from_Cart(String productName) throws InterruptedException {
          List<WebElement> products=cat.getProductList();
          cat.addProdtoCart(productName);
	}
	
	@When("^Checkout (.+) and Submit the order$")
	public void Checkout_And_Submit_Order(String productName) {
		cartPage=cat.cart();
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		checkout=cartPage.goToCheckout();
		checkout.selectCountry("India");
		 cM=checkout.placeOrder();
	}
	
	@Then("Verify {string} message is displayed or not")
	public void verify_message_isDisplayed_or_not(String string) {
		String message=cM.confirmmsg();
		Assert.assertTrue(message.equalsIgnoreCase(string));
	}

}
