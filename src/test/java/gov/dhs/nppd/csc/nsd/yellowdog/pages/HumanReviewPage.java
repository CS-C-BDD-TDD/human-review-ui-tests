package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;

public class HumanReviewPage extends PageObject {

    @FindBy(id = "message")
    WebElement message;	

	public void check_for_pending_message() {
		assertTrue(message.getText().contains("Pending"));
	}
	
	public List<String> getActions() {
		return findAll(".view").stream().map(WebElementFacade::getText).collect(Collectors.toList());
	}
	
}