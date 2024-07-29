package qa.opencart.base;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;
import qa.opencart.factory.PlaywrightFactory;
import qa.opencart.pages.HomePage;
import qa.opencart.pages.LoginPage;

public class BaseTest {
	
	PlaywrightFactory playwrightFactory;
	Page page;  // creating own page reference
	protected HomePage homePage; 
	protected LoginPage loginPage;   
	protected Properties prop;  // creating reference

	@Parameters({"browser"})
	@BeforeTest	
	public void setup(String browserName) {
		playwrightFactory= new PlaywrightFactory();
		//playwrightFactory.initializeProperties() returns prop , so save in prop 
		prop=playwrightFactory.initializeProperties();  
		//page refernce 
		//page = playwrightFactory.initBrowser("chromium");  // passing browser chromium
		if(browserName!=null) {
			prop.setProperty("browser", browserName);  // at runtime it will take browser name from testng parameters and set it in config.properties each time
		}
		
		page = playwrightFactory.initBrowser(prop);  
		homePage= new HomePage(page);  // calling the contructor of homepage and passing page 
		
	}		
	
	@AfterTest
	public void teatDown() {
		page.context().browser().close();		
	}
}
