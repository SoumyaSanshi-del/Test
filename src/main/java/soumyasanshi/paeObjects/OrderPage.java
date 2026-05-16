package soumyasanshi.paeObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import soumyasanshi.CommonThings.AbstractComponents;

public class OrderPage extends AbstractComponents{

	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="tbody td:nth-child(3)")
	List<WebElement> itemsOrdered;

	
	public Boolean verifyOrderDisplay(String prodName) {
		Boolean match=itemsOrdered.stream().anyMatch(p->p.getText().equalsIgnoreCase(prodName));
		return match;
	}
	

}
