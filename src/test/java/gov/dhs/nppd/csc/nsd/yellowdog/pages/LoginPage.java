package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.By;

public class LoginPage extends PageObject {

	public void signin(String username, String password) {
		WebElement user = getDriver().findElement(By.xpath("//input[@type='username']"));
		WebElement pwd = getDriver().findElement(By.xpath("//input[@type='password']"));
		WebElement signin = getDriver().findElement(By.xpath("//button[@type='submit']"));
						
		user.sendKeys(username);
		pwd.sendKeys(password);
		if (! pwd.toString().isEmpty()) {
			WebElement eye = getDriver().findElement(By.xpath("//i[contains(@class, 'fa-eye-slash')]"));
			eye.click();
		}
		signin.click();
	}
	
	public List<String> getActions() {
		return findAll(".view").stream().map(WebElementFacade::getText).collect(Collectors.toList());
	}
	
}