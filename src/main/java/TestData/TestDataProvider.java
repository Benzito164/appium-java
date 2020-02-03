package TestData;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
	@DataProvider(name = "InputData")
	public Object[][] getInputData(){
		var obj = new Object[][]{
				{"Hello"},{"World"}
		};

		return obj;
	}
}
