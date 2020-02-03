package Helpers;

import Base.BaseDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FrameWorkHelper {


	static String currentUserDirectory = System.getProperty("user.dir");

	public static void startAndroidEmulator()throws IOException {
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"/src/main/java/Resources/RunEmulator.sh");

	}

	public static void getScreenShot(String name) throws IOException{
		var screenShotFileObject = ((TakesScreenshot) BaseDriver.getCurrentAppiumDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShotFileObject,new File(String.format("%s/src/main/java/screenshots/%s.png", currentUserDirectory, name)));
	}

	public static  String getPropertyValue(String key) throws Exception {
	FileInputStream file = new FileInputStream(currentUserDirectory+"/src/main/java/Resources/Config.properties");
	Properties property = new Properties();
	property.load(file);
		var value = property.get(key).toString();
	return value;
}
}
