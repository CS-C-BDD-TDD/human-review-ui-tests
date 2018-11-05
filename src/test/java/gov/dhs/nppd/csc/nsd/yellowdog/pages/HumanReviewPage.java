package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class HumanReviewPage extends PageObject {
	private static String STIX_ENTRY_TEMPLATE = "//td[@class='monofont' and contains(text(),'%s')]";

	@FindBy(xpath = "//div[@class='q-table-title']")
	WebElement pendingMessage;

	public void check_for_pending_message() {
		assertTrue(pendingMessage.getText().contains("Pending"));
	}

	public List<String> getActions() {
		return findAll(".view").stream().map(WebElementFacade::getText).collect(Collectors.toList());
	}

	public void click_stix_id(String stixId) {
		String xpath = String.format(STIX_ENTRY_TEMPLATE, stixId);
		getDriver().findElement(By.xpath(xpath)).click();
	}

	public int getNumberOfFields() {
		return getDriver().findElements(By.xpath("//table//table//tbody//tr")).size();
	}

}