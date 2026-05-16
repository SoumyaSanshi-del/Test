package soumyasanshi.paeObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import soumyasanshi.CommonThings.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder*='Country']")
	WebElement selectCountry;
	
	By wait=By.cssSelector("section[class*='ta-results']");
	
	@FindBy(css="button[class*='list-group']")
	List<WebElement> contriesList;
	
	@FindBy(css="a[class*='btnn']")
	WebElement placeOrder;

	public void selectCountry(String countryName) {
		Actions action=new Actions(driver);
		action.sendKeys(selectCountry, countryName).build().perform();
		
		ExWaitvisible(wait);
		
		for(int i=0;i<=contriesList.size();i++) {
			if(contriesList.get(i).getText().equalsIgnoreCase("India")) {
				contriesList.get(i).click();
				break;
			}
			
		}
	}
	
	public ConfirmationPage  placeOrder(){
		placeOrder.click();
		ConfirmationPage cp=new ConfirmationPage(driver);
		return cp;
	}
	
}
