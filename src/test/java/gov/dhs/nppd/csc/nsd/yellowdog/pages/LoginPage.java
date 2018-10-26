package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;

public class LoginPage extends PageObject {

    @FindBy(xpath = "//input[@type='text']")
    WebElement user;
    @FindBy(xpath = "//input[@type='password']")
    WebElement pwd;
    @FindBy(xpath = "//button[@type='button']")
    WebElement signin;
    @FindBy(xpath = "//i[contains(@class, 'fa-eye-slash')]")
    WebElement eye;
    @FindBy(xpath = "//q-alert")
    WebElement err;

	public void signin(String username, String password) {
		user.sendKeys(username);
		pwd.sendKeys(password);
		if (! pwd.toString().isEmpty()) {
			eye.click();
		}
		signin.click();
	}

	public void check_for_error_message() {
		//assertTrue(err.isDisplayed());
	}

	public List<String> getActions() {
		return findAll(".view").stream().map(WebElementFacade::getText).collect(Collectors.toList());
	}
	
}