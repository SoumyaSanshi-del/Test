package soumyasanshi.paeObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import soumyasanshi.CommonThings.AbstractComponents;

public class ProductCatalogePage extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalogePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//div[contains(@class,'mb-3 ')]")
	List<WebElement> list;
	
	By productby=By.cssSelector(".mb-3");
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartclick;
	
	By visible=By.cssSelector("#toast-container");
	
	@FindBy(css=".ng-animating")
	WebElement invisible;
	
	By byCart=By.cssSelector(".card-body button:last-of-type");
	public List<WebElement> getProductList(){
		return list;
	}
	
	public WebElement products(String selectedItem) {
		WebElement prod=getProductList().stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals(selectedItem)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProdtoCart(String selectedItem) throws InterruptedException {
		WebElement prod=products(selectedItem);
		prod.findElement(byCart).click();
		ExWaitvisible(visible);
		ExWaitInvisible(invisible);
	}

}
