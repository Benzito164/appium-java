package Resources;
import Base.BaseDriver;
import Helpers.AppiumServerHelper;
import Helpers.FrameWorkHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListeners implements  ITestListener {

	@Override
	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub
	BaseDriver.QuitCurrentDriver();

	}

	@Override
	public void onStart(ITestContext result)  {
		// TODO Auto-generated method stub
		try {
			AppiumServerHelper.killAllNodes();
			AppiumServerHelper.startAppiumServer();
			BaseDriver.initializeAppiumDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(result.getName()
				+" failed because "+
				result.getThrowable().getMessage()
		);

		try {
			FrameWorkHelper.getScreenShot(result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}
}
