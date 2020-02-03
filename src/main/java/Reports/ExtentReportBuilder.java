package Reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExtentReportBuilder implements IReporter {
	private ExtentReports extent;
	ExtentHtmlReporter htmlReporter;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/src/main/java/Reports/ExtentHtmlReport.html");
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), Status.PASS);
				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
			}
		}

		extent.flush();


	}

	private void buildTestNodes(IResultMap tests, Status status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);


				if (result.getThrowable() != null){
					try {
						var path = "../screenshots/"+result.getName()+".png";
						test.addScreenCaptureFromPath(path).log(status,result.getName()
								+" failed because "+
								result.getThrowable().getMessage());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}



			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}