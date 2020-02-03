package ScreenObjects;

import Base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Helpers.WebElementHelper.waitForElement;

public class SamplePageObjects extends BasePage {


	@AndroidFindBy(xpath = "//*[@text='continue']")
	@iOSXCUITFindBy(id = "IosTextField")
	WebElement continueBtn;

	@AndroidFindBy(id = "TextFieldList")
	@iOSXCUITFindBy(id = "IosTextFieldList")
	List<WebElement> someElementList;

	public void ClickContinue() throws InterruptedException {
		waitForElement(continueBtn).click();
		Thread.sleep(7000);
	}
}
