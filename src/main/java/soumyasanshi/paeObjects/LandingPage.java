package soumyasanshi.paeObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import soumyasanshi.CommonThings.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(css="#userPassword")
	WebElement password;
	
	@FindBy(css="input[type='submit']")
	WebElement submit;
	
	@FindBy(css="div[role='alert']")
	WebElement Error;
	
	public ProductCatalogePage login(String emailId,String pass) {
		email.sendKeys(emailId);
		password.sendKeys(pass);
		submit.click();
		ProductCatalogePage cat=new ProductCatalogePage(driver);
		return cat;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
	}
	
	public String getErrorMessage() {
		ExWaitWebElementToAppear(Error);
		return Error.getText();
	}
	

}
