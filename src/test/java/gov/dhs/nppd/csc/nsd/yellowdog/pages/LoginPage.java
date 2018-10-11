package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;

public class LoginPage extends PageObject {

    @FindBy(xpath = "//input[@type='username']")
    WebElement user;
    @FindBy(xpath = "//input[@type='password']")
    WebElement pwd;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement signin;
    @FindBy(xpath = "//i[contains(@class, 'fa-eye-slash')]")
    WebElement eye;

	public void signin(String username, String password) {
		user.sendKeys(username);
		pwd.sendKeys(password);
		if (! pwd.toString().isEmpty()) {
			eye.click();
		}
		signin.click();
	}
	
	public List<String> getActions() {
		return findAll(".view").stream().map(WebElementFacade::getText).collect(Collectors.toList());
	}
	
}