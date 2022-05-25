package nag.worldcup.pageObject;

import org.openqa.selenium.By;

public interface Objects {
	By searchbox = By.xpath("//input[@id='search']");
	By searchIcon = By.id("search-icon-legacy");
	By videoList = By.xpath(
			"//div[@id='contents' and @class='style-scope ytd-section-list-renderer']//div[@id='contents']//div[@id='dismissible']");
	By filterIcon = By.xpath("//*[text()='Filters']");

	By videoFilter=By.xpath("//div[@title='Search for Video']");
	
	By videoPostedOn = By
			.xpath(".//div[@id='meta']//div[@id='metadata']//span[@class='style-scope ytd-video-meta-block']");

	By videoViews = By
			.xpath(".//div[@id='meta']//div[@id='metadata']//span[@class='style-scope ytd-video-meta-block']");

	By videoDuration = By.xpath(".//span[@class='style-scope ytd-thumbnail-overlay-time-status-renderer']");

	By videoTitle = By.xpath(".//a[@id='video-title']");

	By videoUrl = By.xpath(".//a[@id='thumbnail']");

	By loginInput = By.xpath("//input[@type=\"email\"]");

	By passwordInput = By.xpath("//input[@type=\"password\"])");

	By submit = By.xpath("//input[@type=\"submit\"]");

	By communities = By.xpath("//div[text()='Communities']");

	By allButton = By.xpath("//span[text()='All']");

    By groupName = By.xpath("//span[contains(@class,'groupName')]");

    By membersCount = By.xpath("//div[contains(@class,'inner')]/div/span");


}
