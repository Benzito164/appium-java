package Base;

import Helpers.FrameWorkHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static Helpers.FrameWorkHelper.getPropertyValue;

public class BaseDriver {
	static AppiumDriver driver;

	public static AppiumDriver getCurrentAppiumDriver(){
		return driver;
	}

	public static AppiumDriver initializeAppiumDriver() throws  Exception{
			return driverCapabilities();
	}

	static String executingPlatform;
	static {
		try {
			executingPlatform = (System.getProperty("platform")== null)?
						FrameWorkHelper.getPropertyValue("Platform")
						:System.getProperty("platform");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String deviceName;
	static {
		try {
			deviceName = (System.getProperty("deviceName")== null)?
						(executingPlatform.contains("ios"))? getPropertyValue("IosDeviceName"):
								getPropertyValue("AndroidDeviceName"):
						System.getProperty("deviceName");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private  static AppiumDriver driverCapabilities() throws Exception {
		System.out.println(executingPlatform);
		System.out.println(deviceName);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
		if (executingPlatform.equals("ios")){
			capabilities.setCapability(MobileCapabilityType.APP, getPropertyValue("AppName"));
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			capabilities.setCapability("nativeWebTap", true);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"13.1");
			capabilities.setCapability(MobileCapabilityType.UDID, getPropertyValue("Udid"));
			driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		} else {
			capabilities.setCapability(MobileCapabilityType.APP, getPropertyValue("AndroidAppName"));
			if (getPropertyValue("AndroidDeviceName").contains("Emulator")){
				capabilities.setCapability("avd",deviceName);
			}
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	public static void QuitCurrentDriver(){
		driver.quit();

	}

	public static void CloseCurrentDriver(){
		driver.close();
	}

}
