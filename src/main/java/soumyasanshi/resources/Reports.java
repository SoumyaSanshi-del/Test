package soumyasanshi.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports{
	
	public static ExtentReports getReports() {
		String path=System.getProperty("user.dir")+"\\Reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Case report");
		reporter.config().setReportName("SELENIUM REPORTS");
		
		ExtentReports reports=new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester","Don");
		
		return reports;
		
	}

}
