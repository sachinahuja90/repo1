
package nag.worldcup.testcases;

import nag.worldcup.customException.ElementNotClickable;
import nag.worldcup.customException.ElementNotFound;
import nag.worldcup.pageObject.EmailObjects;
import nag.worldcup.pageObject.Objects;
import nag.worldcup.reporterLogger.ReportFactory;
import nag.worldcup.testcasebase.TestCasesBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Test1 extends TestCasesBase {

	private static String currentDir = System.getProperty("user.dir");

	@Test(description = "Get the Communities details")
	public void getCommunities() throws ElementNotClickable, ElementNotFound, IOException {
		ReportFactory.getInstance().info("Launching the application - " + configProperties.get("url"));
		excelUtils.createExcel(currentDir + "/" + configProperties.get("excelName"));
		String sheetname1 = configProperties.get("sheetname1");
		List<String> cols1 = new ArrayList<String>();
		cols1.add(configProperties.get("sheetname1Col1"));
		cols1.add(configProperties.get("sheetname1Col2"));
		excelUtils.createExclSheet(currentDir + "/" + configProperties.get("excelName"), sheetname1, cols1);
		keywords.navigateTo(configProperties.get("url"));
		keywords.explicitWait(Objects.loginInput);
		keywords.typeText("LoginInput", Objects.loginInput, configProperties.get("emailId"));
		keywords.click("Next", Objects.submit);
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		keywords.explicitWait(Objects.communities);
		keywords.click("CommunitiesLink", Objects.communities);
		ReportFactory.getInstance().info("Communities link clicked");
		keywords.explicitWait(Objects.allButton);
		keywords.click("AllButton", Objects.allButton);
		ReportFactory.getInstance().info("All button clicked.");
		keywords.scrollWindowToBottom();
		keywords.scrollWindowToBottom();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Map<String, Integer> maptest = new LinkedHashMap<String, Integer>();
		List<WebElement> grpNameList = keywords.getElementList("GroupName", Objects.groupName);
		List<WebElement> memberCountList = keywords.getElementList("MemberCount", Objects.membersCount);
		for (int i = 0; i < grpNameList.size(); i++) {
			int item = Integer.parseInt(memberCountList.get(i).getText().replaceAll(",", ""));
			maptest.put(grpNameList.get(i).getText(), item);
		}
		Map<String, Integer> sortedMap = utility.sortByValue(true, maptest);
		excelUtils.writeExcel(currentDir + "/" + configProperties.get("excelName"), sheetname1, sortedMap);
	}

	@Test(description = "Get the Emails details", priority = 2)
	public void getEmails() throws ElementNotClickable, ElementNotFound, IOException {
		String sheetname = configProperties.get("sheetname2");
		List<String> cols1 = new ArrayList<String>();
		cols1.add(configProperties.get("sheetname2Col1"));
		cols1.add(configProperties.get("sheetname2Col2"));
		cols1.add(configProperties.get("sheetname2Col3"));
		excelUtils.createExclSheet(currentDir + "/" + configProperties.get("excelName"), sheetname, cols1);
		ReportFactory.getInstance().info("All button clicked.");

		keywords.waitForWindowToLoad();
		keywords.explicitWait(EmailObjects.nqlb);
		keywords.click("NQLB", EmailObjects.nqlb);
		String urlNQLB = keywords.getCurrentURL();

		Map<String, String> map = new TreeMap<String, String>();

		int i = 0, j = 0;
		int maxMemberCount = Integer.parseInt(configProperties.get("maxMemberCount"));
		while (i < maxMemberCount) {
			try {
				keywords.explicitWait(EmailObjects.contactCount);
				keywords.click("NQLB", EmailObjects.contactCount);
				keywords.explicitWait(EmailObjects.comMembers);
				List<WebElement> list = keywords.getElementList("Community Members", EmailObjects.comMembers);
				list.get(j).click();
				keywords.explicitWait(EmailObjects.contactCard);
				keywords.click("", EmailObjects.contactCard);
				keywords.explicitWait(EmailObjects.emailAddress);
				String email = keywords.getText("Email Address", EmailObjects.emailAddress);
				String name = keywords.getText("MemberName", EmailObjects.memberName);
				String teamName = keywords.getText("DeptName", EmailObjects.deptName);
				map.put(name, email + ";" + teamName);
				keywords.click("Close Contact Card", EmailObjects.closeCard);
				keywords.navigateTo(urlNQLB);
				keywords.waitForWindowToLoad();
				i++;
			} catch (Exception e) {
				keywords.navigateTo(urlNQLB);
				keywords.waitForWindowToLoad();
				System.out.println(e.getMessage());
			}
			j++;
		}
		excelUtils.writeExcel1(currentDir + "/" + configProperties.get("excelName"), sheetname, map);
	}

	@Test(description = "Get the getTestPractice details", priority = 2)
	public void getTestPractice() throws ElementNotFound, ElementNotClickable, IOException {
		keywords.navigateTo(configProperties.get("TestAutomationPracticeURL"));
		String sheetname = configProperties.get("sheetname3");
		List<String> cols1 = new ArrayList<String>();
		cols1.add(configProperties.get("sheetname3Col1"));
		cols1.add(configProperties.get("sheetname3Col2"));
		cols1.add(configProperties.get("sheetname3Col3"));
		cols1.add(configProperties.get("sheetname3Col4"));
		excelUtils.createExclSheet(currentDir + "/" + configProperties.get("excelName"), sheetname, cols1);

	}

}
