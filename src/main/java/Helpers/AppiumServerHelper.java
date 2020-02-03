package Helpers;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class AppiumServerHelper {
	static AppiumDriverLocalService appiumDriverLocalService;

	public static AppiumDriverLocalService startAppiumServer(){
		if (!checkIfServerIsRunning(4723)){
			appiumDriverLocalService  = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
					.withIPAddress("127.0.0.1")
					.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
					.withArgument(GeneralServerFlag.LOG_LEVEL,"error")
					.withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
					.usingPort(4723));
			appiumDriverLocalService.start();
		}

			return appiumDriverLocalService;
	}

	public static void stopAppiumServer(){
		appiumDriverLocalService.stop();
	}

	public static boolean checkIfServerIsRunning(int port) {
		var isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}

		return isServerRunning;
	}

	public static void killAllNodes() throws Exception {
		Runtime.getRuntime().exec("killall node");
		Thread.sleep(3000);
	}
}
