# GUI Test Automation using Selenium

## Features supported
1. Dependency management and project management by using MAVEN 
2. Functional UI Automation by using Selenium in Parallel Multi Browser support
3. TestNG for testing workflow
4. GIT as distributed version-control system
5. Modular Approach via Page Object model
6. Browser supported - Chrome, IE and Firefox
7. WebDriver Manager is Implemented to avoid Dependency over Browser Driver Versions.
8. HTML Report by including Extent Reports
9. Logging via Log4j
10. Property Reader to read Test data from properties files.
11. Archived Last execution results by utilizing `java.util.zip`. 
12. Custom exception is created to avoid reading false files, Object Not Found, Zip Creation Exception.
13. TestNG Listeners to tackle Skipped testcase in extent report.
14. Custom assertion to print custom message when assertion fails.
15. Capture screen shots for failed testcases.
16. Implicit and Explicit waits are considered for Windows to Load and Webelement to load.
17. Maven is configured in such a way that will run different testNG.xml provided at run time.


## Pre-requisite
1. Java above 1.8.
2. Maven version above 3.0.
3. TestNG 6.14.3

## How to install & Run using command prompt
1. Please extract the project at your desired path.
2. Update maven to update dependencies
3. Go to `src/test/resources/config/config.properties` file and update configurations. 
4. Open the command prompt and go to the project path.
5. Run the command "mvn clean install -DsuiteXmlFile=ParallelTestSuite.xml"
6. All the automated test cases in the testNG.xml will be executed.

Or From Eclipse IDE using TestNG

1. Import Maven Project
2. Update maven to update dependencies
3. Run ParallelTestSuite.xml via TestNG plugin.

		
## To view Report - Refresh the Project
1. Go to the root directory under `SeleniumProject/Current_test_results/<yyyy-mm-dd hh-mm-ss>/testReport.html`
2. All past reports are saved under `SeleniumProject/Arhived_test_results/<yyyy-mm-dd hh-mm-ss>.zip/testReport.html` 



