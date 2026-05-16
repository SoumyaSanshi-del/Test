package soumyasanshi.BaseCommonTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import soumyasanshi.paeObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initalizeDriver() throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\soumyasanshi\\resources\\Global.properties");
		prop.load(fis);
		
//		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		String browserName=prop.getProperty("browser");
		
//		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
		 driver.manage().window().setSize(new Dimension(1440,900));
//		}else if(browserName.equalsIgnoreCase("firefox")) {
//			driver=new FirefoxDriver();
//		}else if(browserName.equalsIgnoreCase("firefox")) {
//			driver=new EdgeDriver();
//		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage lauchApplication() throws IOException {
		
		driver=initalizeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void EndApp() {
		driver.close();
	}
	
public List<HashMap<String, String>> getJsonDataTOFile() throws IOException {
		
		//Converting json to string
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\soumyasanshi\\parameterFiles\\PurchaseOrder.json"),StandardCharsets.UTF_8);
		
		//String to HashMap{need jackson databind dependency}
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}

       public String getScreenShot(String testcaseName,WebDriver driver) throws IOException {
    	   TakesScreenshot ts=(TakesScreenshot)driver;
    	   File src=ts.getScreenshotAs(OutputType.FILE);
    	   File des=new File(System.getProperty("user.dir")+"\\reports"+testcaseName+".png");
    	   FileUtils.copyFile(src, des);
    	   
    	   return System.getProperty("user.dir")+"\\reports"+testcaseName+".png";
        }


}
