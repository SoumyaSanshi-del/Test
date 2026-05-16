package soumyasanshi.CommonThings;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import soumyasanshi.paeObjects.CartPage;
import soumyasanshi.paeObjects.CheckOutPage;
import soumyasanshi.paeObjects.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void ExWaitvisible(By findby) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void ExWaitWebElementToAppear(WebElement findby) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}

	public void ExWaitInvisible(WebElement element) throws InterruptedException {
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(element));
		Thread.sleep(2000l);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement kart;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement order;
	
	public CartPage cart() {
		kart.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage orders() {
		order.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
	
}
