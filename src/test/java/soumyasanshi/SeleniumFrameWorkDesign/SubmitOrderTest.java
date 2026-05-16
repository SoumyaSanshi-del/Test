package soumyasanshi.SeleniumFrameWorkDesign;

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

public class SubmitOrderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/#/auth/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String itemName="ZARA COAT 3";
		//Enter Username and Passowrd
		driver.findElement(By.id("userEmail")).sendKeys("pinky2000@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Pinky@#2000");
		
		//Click on Login
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		
		//List all elements
		List<WebElement> list=driver.findElements(By.xpath("//div[contains(@class,'mb-3 ')]"));
		
		for(int i=0;i<list.size();i++) {
			WebElement ele=list.get(i).findElement(By.tagName("b"));
			if(ele.getText().contains(itemName)){
				list.get(i).findElement(By.cssSelector(".card-body button:last-of-type")).click();
			}
		}
		
		//wait untill the alert prodcut added to cart
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//Click on add to cart
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		//In cartsection list of ietms
		List<WebElement> itemsCarted=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match=itemsCarted.stream().anyMatch(s->s.getText().equals(itemName));
		Assert.assertTrue(match);
		
		//click on checkout button
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		//Send countryName
		WebElement ele=driver.findElement(By.cssSelector("input[placeholder*='Country']"));
		Actions action=new Actions(driver);
		action.sendKeys(ele, "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='ta-results']")));
		
		List<WebElement> countries=driver.findElements(By.cssSelector("button[class*='list-group']"));
		for(int i=0;i<=countries.size();i++) {
			if(countries.get(i).getText().equalsIgnoreCase("India")) {
				countries.get(i).click();
				break;
			}
		}
		
		//click on place order
		driver.findElement(By.cssSelector("a[class*='btnn']")).click();
		
		//grab orderId
		String ty=driver.findElement(By.cssSelector(".hero-primary")).getText();
		ty.equalsIgnoreCase("Thankyou for the order.");
		driver.close();
		
		

	}

}
