package nag.worldcup.testcasebase;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import nag.worldcup.client.BrowserFactory;
import nag.worldcup.common.util.ExcelUtils;
import nag.worldcup.common.util.Utilities;
import nag.worldcup.customAssertion.CustomAssertion;
import nag.worldcup.propertyReader.PropertyReader;
import nag.worldcup.reporterLogger.ReportFactory;
import nag.worldcup.selenium.keywords.SeleniumKeywords;

public class TestCasesBase {

	private static String curDir = System.getProperty("user.dir");
	protected static CustomAssertion m_custom;
	public static final Logger LOGGER = Logger.getLogger(TestCasesBase.class);

	public static HashMap<String, String> configProperties;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest extentTest;
	protected WebDriver driver;
	
	public static ExcelUtils excelUtils;
	public static Utilities utility;
	public static SeleniumKeywords keywords;
	private static String currentFolder;
	

	@BeforeSuite
	public void beforeSuite() throws Exception {
		try {
			initializePropertyFiles();
			initializeKeywords();
			currentFolder = curDir + ReportFactory.reportPropertyMap.get("htmlReportFolder");

			File[] directories = new File(currentFolder).listFiles(File::isDirectory);
			if (!(new File(currentFolder).exists()))
				new File(currentFolder).mkdir();
			else if (directories.length > 0) {
				utility.archieveLastReports(directories[0].getPath());
				LOGGER.info("Archived Previous Reports.");
			}
			currentFolder = currentFolder + "/"
					+ (utility.getCurrentDateTime().replaceAll("/", "-").replaceAll(":", "-"));
			new File(currentFolder).mkdir();
			
			System.out.println(currentFolder);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browser) throws Exception {
		ReportFactory.reportFolder = currentFolder;
		SeleniumKeywords.waitTime = Integer.parseInt(configProperties.get("MaxwaitTime"));
		ReportFactory.getInstance().generateReport(browser);
		BrowserFactory.getInstance().launchBrowser(browser);
		driver = BrowserFactory.getInstance().getDriver();
	}

	// This method will be executed before each TEST Method.
	@BeforeMethod
	public void initializeRestClient(Method method, ITestResult result) throws Exception {
		ReportFactory.getInstance().newTest(method.getName(), result);
//		keywords.navigateTo(configProperties.get("testURL"));
	}

	// This method will be executed after each TEST Method weather it is
	// PASS/FAIL/Skipped.

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			ReportFactory.getInstance().fail(result);
		} else if (result.getStatus() == ITestResult.SUCCESS)
			ReportFactory.getInstance().pass(result);

		else
			ReportFactory.getInstance().skipped(result);

	}

	// This method will execute after completing the suite.
	@Parameters("browser")
	@AfterTest
	public void tearDown(String browser) {
		// to write or update test information to reporter
		ReportFactory.getInstance().printReport(browser);
		BrowserFactory.getInstance().quitDriver();
	}

	private static void initializeKeywords() {
		keywords = new SeleniumKeywords();
		utility = new Utilities();
		m_custom = new CustomAssertion();
		excelUtils=new ExcelUtils();
	}

	private static void initializePropertyFiles() {
		try {
			ReportFactory.reportPropertyMap = new PropertyReader()
					.getProperties(curDir + "\\src\\test\\resources\\extentReport.properties");
			configProperties = new PropertyReader().getProperties(curDir + "\\src\\test\\resources\\config.Properties");
		} catch (Exception e) {
			ReportFactory.LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
	}
}