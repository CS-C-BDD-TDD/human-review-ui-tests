package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import gov.dhs.nppd.csc.nsd.yellowdog.util.CommonUtil;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;

public class YellowDogPage extends PageObject {

	@FindBy(xpath = "//input[@type='text']")
	private WebElement userNameText;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordText;

	@FindBy(xpath = "//button")
	private WebElement loginButton;

	@FindBy(xpath = "//div[contains(text(),'Login failed')]")
	private WebElement loginDenialErrorLabel;

	public void visit(String websiteUrl) {
		if ("Yes".equalsIgnoreCase(CommonUtil.get("hr.webdriver.maxpage"))) {
//			getDriver().manage().window().maximize();
			Dimension d = new Dimension(1600, 1200);
			// Resize current window to the set dimension
			getDriver().manage().window().setSize(d);
		}
		this.openAt(websiteUrl);
	}

	public void enterUserName(String username) {
		userNameText.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordText.sendKeys(password);
	}

	public void clickLoginButton() {
		loginButton.click();
	}

	public String getLoginDenialMsg() {
		return loginDenialErrorLabel.getText();
	}

}