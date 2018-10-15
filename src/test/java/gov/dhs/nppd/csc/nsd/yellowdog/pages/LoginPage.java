package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends PageObject {

	@FindBy(xpath = "//input[@type='username']")
	WebElement user;
	@FindBy(xpath = "//input[@type='password']")
	WebElement pwd;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement signin;
	@FindBy(xpath = "//i[contains(@class, 'fa-eye-slash')]")
	WebElement eye;
	@FindBy(id = "error")
	WebElement err;

	@FindBy(xpath = "//h4[@class='authenticated']")
	WebElement welcomeMsg;

	@FindBy(xpath = "//input[@type='username']")
	WebElement usernameElement;

	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordElement;

	@FindBy(xpath = "//div[contains(text(),'Sign In')]")
	WebElement signInButton;

	public void signin(String username, String password) {
		user.sendKeys(username);
		pwd.sendKeys(password);
		if (!pwd.toString().isEmpty()) {
			eye.click();
		}
		signin.click();
	}

	public void check_for_error_message() {

	}

	public List<String> getActions() {
		return findAll(".view").stream().map(WebElementFacade::getText).collect(Collectors.toList());
	}

	public String getWelcomeMsg() {
		return welcomeMsg.getText();
	}

	public void enterUsername(String username) {
		usernameElement.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordElement.sendKeys(password);
	}

	public void clickSignInButton() {
		signInButton.click();
	}

}