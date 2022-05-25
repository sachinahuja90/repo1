package nag.worldcup.pageObject;

import org.openqa.selenium.By;

public interface EmailObjects {
	By emailAddress=By.xpath("(//div[@data-log-name='Email.Email']//span)[1]");
	By memberName=By.xpath("//div[@data-log-name='PersonName']");
	By deptName=By.xpath("//span[@data-log-name='Department']");
	By contactCard=By.xpath("//button[@aria-label='Show contact information']");
	
	By contactCount=By.xpath("//span[text()='Members']/parent::h2/following-sibling::span//span");
	By nqlb=By.xpath("//div[@title='NQLB - No QA Left Behind']//span[text()='NQLB - No QA Left Behind']");
	
	By practice=By.xpath("//div[@title='Test Automation Practice']//span[text()='Test Automation Practice']");
	String membersFrame="o365shellwcssframe";
	By comMembers =By.xpath("//Span[text()='Community members']/parent::div//following-sibling::ul//a//span[@dir='auto']");
	By closeCard=By.xpath("//button[@data-log-name='CloseButton']");
	
}
