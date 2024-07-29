package qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.opencart.base.BaseTest;
import qa.opencart.constants.AppConstants;

public class HomePageTest extends BaseTest{

	@Test
	public void homePageTitleTest () {
		String actualTitle=homePage.getHomePageTitle();		
		Assert.assertEquals(actualTitle, AppConstants.Home_Page_Title);
	}
	
	@Test
	public void homePageUrlTest() {
		String actualUrl=homePage.getHomePageUrl();
		//Assert.assertEquals(actualUrl, "https://naveenautomationlabs.com/opencart/");
		Assert.assertEquals(actualUrl, prop.getProperty("url"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook"},
			{"Samsung"},
			{"iPhone"}
		};
	}
	
	@Test(dataProvider="getProductData")
	public void searchTest(String productName) {
//		String  actualSearchHeader= homePage.doSearch("Samsung");
//		Assert.assertEquals(actualSearchHeader, "Search - samsung");	
		String  actualSearchHeader= homePage.doSearch(productName);
		Assert.assertEquals(actualSearchHeader, "Search - "+productName);	
	}

}
