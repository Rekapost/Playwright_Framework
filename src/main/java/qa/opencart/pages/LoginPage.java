package qa.opencart.pages;
import com.microsoft.playwright.Page;

public class LoginPage {
	private Page page;
	
	// 1. String Locators - Object Repository
		private String  emailUsername= "#input-email";
		private String  password= "#input-password";
		private String  loginButton= "input[value='Login']";
		private String forgottenPassword= "div[class='form-group'] a";
		private String logoutLink = "//a[@class='list-group-item'][normalize-space()='Logout']";
	
	// 2. Page constructor
	public LoginPage(Page page) {
		this.page = page;
	}

	// 3. Page actions/methods:
	public String getLoginPageTitle() {
		return page.title();
	}
 
	public boolean isForgottenPasswordLinkExist() {
		return page.isVisible(forgottenPassword);
	}
	
	public boolean doLogin(String appUsername, String appPassword)  {
		System.out.println("Application credentials: "+ appUsername + " : " +appPassword );
		page.fill(emailUsername, appUsername);
		page.fill(password, appPassword);  //admin@123
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.click(loginButton);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(page.isVisible(logoutLink)) {
			System.out.println(" User is logged in successful..... ");
			return true;
		}
		return false;
	}
	
}
