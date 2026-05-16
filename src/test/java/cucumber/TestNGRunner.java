package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="soumyasanshi.cstepdefiniations",monochrome=true,plugin= {"html:taget/cucumber.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests {
	
	public void Runner() {
		
	}

}
