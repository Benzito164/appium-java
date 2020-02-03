package Steps;

import Helpers.FrameWorkHelper;
import ScreenObjects.HomeScreenObjects;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
		features = "src/main/java/Features/",
		strict = true,
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:src/main/java/Reports/CucumberReport.html"}
)

public class TestRunner extends AbstractTestNGCucumberTests {


	@After
	public void afterMethod(Scenario scenario) throws  Exception{
		if(scenario.isFailed()){
			String imageName = scenario.getName();
			FrameWorkHelper.getScreenShot(imageName);
			var path = "../screenshots/"+imageName+".png";
			Reporter.addScreenCaptureFromPath(path);
		}
		new HomeScreenObjects().resetApp();


	}
}
