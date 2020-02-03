package Base;

import org.testng.annotations.*;


public class BaseTest {
	@BeforeSuite
	public void BeforeSuite(){
		System.out.println("BeforeSuite");
	}

	@BeforeTest
	public void BeforeTest(){
		System.out.println("BeforeTest");
	}

	@BeforeClass
	public void BeforeClass(){
		System.out.println("BeforeClass");

	}



	@AfterClass
	public void AfterClass(){
		System.out.println("AfterClass");
	}


	@AfterTest
	public void AfterTest(){
		System.out.println("AfterTest");
	}

	@AfterSuite
	public void AfterSuite(){
		System.out.println("AfterSuite");
	}

}
