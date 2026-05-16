package soumyasanshi.paeObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import soumyasanshi.CommonThings.AbstractComponents;

public class CartPage extends AbstractComponents{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> itemsCarted;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkout;
	
	public Boolean verifyProductDisplay(String prodName) {
		Boolean match=itemsCarted.stream().anyMatch(p->p.getText().equalsIgnoreCase(prodName));
		return match;
	}
	
	public CheckOutPage goToCheckout() {
		checkout.click();
		CheckOutPage checkout=new CheckOutPage(driver);
		return checkout;
	}

}
