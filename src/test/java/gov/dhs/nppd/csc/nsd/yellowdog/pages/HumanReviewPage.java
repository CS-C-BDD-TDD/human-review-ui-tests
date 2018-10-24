package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;

public class HumanReviewPage extends PageObject {

    @FindBy(xpath = "//div[@class='q-table-title']")
    WebElement pendingMessage;	

	public void check_for_pending_message() {
		assertTrue(pendingMessage.getText().contains("Pending"));
	}
	
	public List<String> getActions() {
		return findAll(".view").stream().map(WebElementFacade::getText).collect(Collectors.toList());
	}
	
}