package ScreenObjects;

import Base.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import java.util.List;

import static Helpers.WebElementHelper.*;


public class HomeScreenObjects extends BasePage {

	@CacheLookup
	@AndroidFindBy(id = "gdpr_intro_text")
	@iOSXCUITFindBy(accessibility = "locationSearchButton")
	private MobileElement searchButton;

	@CacheLookup
	@AndroidFindBy(id = "gdpr_intro_text")
	@iOSXCUITFindBy(accessibility = "restartButton")
	private MobileElement restartIcon;

	@CacheLookup
	@AndroidFindBy(id = "gdpr_intro_text")
	@iOSXCUITFindBy(xpath = ".//*[@nam='Reset']")
	private MobileElement resetButton;

	@CacheLookup
	@AndroidFindBy(id = "gdpr_intro_text")
	@iOSXCUITFindBy(accessibility = "Add Location")
	private	 MobileElement addLocationButton;

	@CacheLookup
	@AndroidFindBy(id = "gdpr_intro_text")
	@iOSXCUITFindBy(accessibility = "Dismiss")
	private MobileElement dismissLocationButton;

	@CacheLookup
	@AndroidFindBy(id = "gdpr_intro_text")
	@iOSXCUITFindBy(accessibility = ".//*[@value='Site Address (URL)']")
	private MobileElement locationNameHeaderDisplay;

	@CacheLookup
	@AndroidFindBy(id = "gdpr_intro_text")
	@iOSXCUITFindBy(accessibility = "Cancel")
	private MobileElement uiSearchCancelButton;

	@CacheLookup
	@AndroidFindBy(id = "gdpr_intro_text")
	@iOSXCUITFindBy(accessibility = "Search")
	private MobileElement uiSearchLocationTextfield;

	@CacheLookup
	@AndroidFindBy(id = "gdpr_intro_text")
	@iOSXCUITFindBy(xpath = ".//*[@type='XCUIElementTypeCell']")
	private List<MobileElement> uiLocationResultTable;

	@CacheLookup
	@AndroidFindBy(id = "gdpr_intro_text")
	@iOSXCUITFindBy(xpath = ".//*[@name='locationLabelIdentifier']")
	private	 MobileElement headerWeatherViewLocationName;

	@CacheLookup
	@AndroidFindBy(id = "gdpr_intro_text")
	@iOSXCUITFindBy(accessibility = ".//*[@value='Site Address (URL)']")
	private MobileElement uiPopUp;

	@CacheLookup
	@iOSXCUITFindBy(accessibility = "weatherCellid")
	private List<MobileElement> weatherInformation;




	public HomeScreenObjects addLocation(String location ){
		waitForElement(searchButton).click();
		uiSearchLocationTextfield.sendKeys(location);
		uiSearchLocationTextfield.sendKeys(" ");
		List<MobileElement> results = uiLocationResultTable;
		for (MobileElement locations : results){
			var locationName = fetchSearchLocationFromTableResults(locations,location).getAttribute("label");
			if (location.contains(locationName)){
				locations.click();
				break;
			}

		}
		waitForElement(addLocationButton).click();
		return this;
	}

	private WebElement fetchSearchLocationFromTableResults(WebElement resultCell,String location){
		location = location.replace(", UK", "'");
		var stringBuffer = new StringBuffer(location);
		stringBuffer.insert(0,"'");
		var locationn = stringBuffer.toString();
		return waitForElement(resultCell.findElement(By.xpath(".//XCUIElementTypeStaticText[@value="+locationn+"]")));
	}

	public HomeScreenObjects deleteLocation(String location){
		WebElement locationForecast = getWeatherInformation(location);
		performLongPress(4,locationForecast);
		return this;
	}

	public HomeScreenObjects selectWeatherLocation(String location){
		waitForElement(getWeatherInformation(location)).click();
		return this;

	}

	public String getHeaderViewLocationName(){
		return waitForElement(headerWeatherViewLocationName).getText();
	}

	public MobileElement getWeatherInformation(String location){
		for (MobileElement locationWeather : weatherInformation){
			String weatherLocation = locationWeather.getAttribute("label");
			try {
				if ((location.contains(weatherLocation))){
					return locationWeather;
				}
			} catch (Exception ex) {
				return null;
			}

		}
		return null;
	}

	public HomeScreenObjects resetApp(){
		try {
			waitForElement(restartIcon).click();
			performTap(resetButton);
		} catch (Exception error) {
			waitForElement(uiSearchCancelButton).click();
			waitForElement(restartIcon).click();
			performTap(resetButton);
		}
		return this;
	}
}
