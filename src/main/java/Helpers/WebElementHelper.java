package Helpers;

import Base.BaseDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class WebElementHelper {

	static WebDriverWait wait = new WebDriverWait(BaseDriver.getCurrentAppiumDriver(), 15);
	static TouchAction action = new TouchAction(BaseDriver.getCurrentAppiumDriver());

	public static WebElement waitForElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	return wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static void performLongPress(int duration, WebElement element){
		action.longPress(LongPressOptions.longPressOptions()
				.withElement(element(element))
				.withDuration(Duration.ofSeconds(duration))).release().perform();
	}

	public static void performTap( WebElement element){
		new TouchAction(BaseDriver.getCurrentAppiumDriver()).tap(TapOptions.tapOptions().withElement(element(element))).perform();

	}

}
