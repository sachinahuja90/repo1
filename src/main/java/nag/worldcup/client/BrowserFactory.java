package nag.worldcup.client;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import nag.worldcup.reporterLogger.ReportFactory;

public class BrowserFactory {

	private static BrowserFactory instance = null;

	public static final Logger LOGGER = Logger.getLogger(BrowserFactory.class);
	private BrowserFactory() {

	}

	public static BrowserFactory getInstance() {
		if (instance == null) {
			instance = new BrowserFactory();
		}
		return instance;
	}

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public final void launchBrowser(String browserType) throws Exception {

		if (browserType.equalsIgnoreCase("Chrome"))
			setChromeDriver();
		else if (browserType.equalsIgnoreCase("Firefox"))
			setFireFoxDriver();
		
		LOGGER.info("Launched Browser - "+browserType);
	}

	private void setChromeDriver() {
		try {
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(chromeOptions));
			driver.get().manage().window().maximize();

		} catch (Exception e) {
			ReportFactory.LOGGER.info(e.getMessage());
			throw e;
		}
	}

	private void setFireFoxDriver() {
		try {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver(firefoxOptions));
		} catch (Exception e) {
			ReportFactory.LOGGER.info(e.getMessage());
			throw e;
		}
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void quitDriver() {
		driver.get().quit();
	}

}
