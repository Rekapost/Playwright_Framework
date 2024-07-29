package qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	private Page page;  // to avoid null pointer exception
	
	//1. String Locators - Object Repository
	private String  inputSearch= "input[name='search']";
	private String  searchIcon= "div#search button";
	private String searchPageHeader = "div#content h1";
	private String loginLink = "a:text('Login')";
	private String myAccountLink = "a[title='My Account']";
	
	//2. Page constructor
	public HomePage(Page page) {
		this .page=page;		
	}
	
	//3. Page actions/methods:
	public String getHomePageTitle() {
		String title= page.title();
		System.out.println("Page title :"+title);
		return title;
		}
	
	public String getHomePageUrl() {
		String url= page.url();
		System.out.println("URL :"+ url);
		return url;
	}
	
	public String doSearch(String productName) {
		page.fill(inputSearch, productName);
		page.click(searchIcon);
		String header= page.textContent(searchPageHeader);
		// page.locator(searchPageHeader);
		System.out.println("Search Header is :" + header);
		return header;
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(myAccountLink);
		page.click(loginLink);
		//page.locator(loginLink).click();
		//it is the responsibility of this method to return the next landing page object
	    return new LoginPage(page);
	
	}
}
