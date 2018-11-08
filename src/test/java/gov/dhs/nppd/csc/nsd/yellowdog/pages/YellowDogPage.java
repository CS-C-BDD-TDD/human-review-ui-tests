package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import org.openqa.selenium.WebElement;

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
		getDriver().manage().window().maximize();
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