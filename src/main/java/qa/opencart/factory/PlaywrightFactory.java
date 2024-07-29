package qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;

	// INITIALIZING THREAD LOCAL
	// whenever we initialize any object with thread local
	// it will give local copy TO specific thread of that particular object
	// it will give local copy of the object and give to individual thread
	private static ThreadLocal<Browser> threadLocalBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> threadLocalBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();
	private static ThreadLocal<Playwright> threadLocalPlaywright = new ThreadLocal<>();
	// every thread will get own respective copy
	// ThreadLocal copy should be private
	// ThreadLocal is used for parallel execution and individual test report
	// first set and then get

	// GET METHOD
	public static Browser getBrowser() {
		return threadLocalBrowser.get(); // it will give local copy of Browser
	}

	public static BrowserContext getBrowserContext() {
		return threadLocalBrowserContext.get(); // it will give local copy of BrowserContext
	}

	public static Page getPage() {
		return threadLocalPage.get(); // it will give local copy of page
	}

	public static Playwright getPlaywright() {
		return threadLocalPlaywright.get(); // it will give local copy of playwright
	}

	// suppose u have 3 threads , all 3 threads will access initBrowser method ,all
	// 3 browser will get the object/page reference of Page

	// public Page initBrowser(String browserName) {
	public Page initBrowser(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser Name :" + browserName);

		// playwright = Playwright.create();
		threadLocalPlaywright.set(Playwright.create()); // set local copy of playwright

		switch (browserName.toLowerCase()) {
		case "chromium":
			// browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			threadLocalBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "firefox":
			// browser= playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			threadLocalBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "safari":
			// browser= playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			threadLocalBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			// browser= playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			threadLocalBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		case "edge":
			// browser= playwright.chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false));
			threadLocalBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false)));
			break;
		default:
			System.out.println("please pass the right browser name......");
			break;
		}

		// browserContext= browser.newContext();
		threadLocalBrowserContext.set(getBrowser().newContext());
		// page=browserContext.newPage();
		threadLocalPage.set(getBrowserContext().newPage());
		// page.navigate("https://naveenautomationlabs.com/opencart/");
		// page.navigate(prop.getProperty("url").trim());
		getPage().navigate(prop.getProperty("url").trim());
		// return page;
		return getPage();
	}

	// this method is used to initialize the properties from config.file
	public Properties initializeProperties() {
		try {
			FileInputStream stream = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(stream);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		// getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        // return path;
		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);

		return base64Path;
	}

}
