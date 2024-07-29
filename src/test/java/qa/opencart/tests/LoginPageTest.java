package qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.opencart.base.BaseTest;
import qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {
	
	//homePage.navigateToLoginPage(); 
	
	@Test(priority =1)
	public void loginPageNavigationTest () {
		loginPage=homePage.navigateToLoginPage();  //Page chaining concept
		String actualTitle=loginPage.getLoginPageTitle();
		System.out.println("Actual Title : "+ actualTitle);
		Assert.assertEquals(actualTitle, AppConstants.Login_Page_Title);
	}
	
	@Test(priority =2)
	public void forgotPasswordLinkExistTest() {
	Assert.assertTrue(loginPage.isForgottenPasswordLinkExist());
	}
	
	//@DataProvider
	public Object[][] getCredentialsData() {
		return new Object[][] {
			{"rekaharisi@gmail.com","admin@23"},
			{"rekahariri@gmail.com","admin@13"},
			{"rekaharisri@gmail.com","admin@123"}
		};
	}
	
	//@Test(dataProvider="getCredentialsData", priority =3)
	@Test(priority =3)
	//public void appLoginTest(String username, String password) {
	public void appLoginTest() {
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
	}

}
