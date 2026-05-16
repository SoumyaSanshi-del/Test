package soumyasanshi.BaseCommonTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import soumyasanshi.resources.Reports;

public class Listeners extends BaseTest implements ITestListener{
	ExtentReports rep=Reports.getReports();
	ExtentTest test;
	ThreadLocal<ExtentTest> td=new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
	    test=rep.createTest(result.getMethod().getMethodName());
	    td.set(test);
	  }
	@Override
	public void onTestSuccess(ITestResult result) {
		td.get().log(Status.PASS, "Test Passed");
	  }
	
	@Override
	public void onTestFailure(ITestResult result) {
		td.get().fail(result.getThrowable());
	    String screenshot = null;
	    try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			screenshot = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		td.get().addScreenCaptureFromPath(screenshot,result.getMethod().getMethodName());
	  }
	
	@Override
	public void onFinish(ITestContext context) {
	    // not implemented
		rep.flush();
	  }
}
