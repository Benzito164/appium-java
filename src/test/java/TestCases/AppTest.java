package TestCases;

import Base.BaseTest;
import ScreenObjects.HomeScreenObjects;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest
{
    /**
     * Rigorous Test :-)
     */
    HomeScreenObjects homeScreen;

   @BeforeTest
   public void beforeTest() throws Exception {
       homeScreen = new HomeScreenObjects();
   }



    @Test
    public void addLocation() throws Exception  {
    var location = "London, UK";
    homeScreen.addLocation(location);
    }

   // @Test
    public void addMultipleLocations() throws Exception  {

    }

   @Test
    public void deleteLocation() throws Exception  {
        addLocation();
        homeScreen.deleteLocation("London, UK");
    }


    @Test
    public void deleteAllLocations() throws Exception  {
        addLocation();
        homeScreen.resetApp();
    }

}
