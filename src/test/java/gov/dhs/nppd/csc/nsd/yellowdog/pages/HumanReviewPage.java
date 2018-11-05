package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class HumanReviewPage extends PageObject {
	private static final Logger logger = LoggerFactory.getLogger(HumanReviewPage.class);
	private static String STIX_ENTRY_TEMPLATE = "//td[@class='monofont' and contains(text(),'%s')]";

	@FindBy(xpath = "//div[@class='q-table-title']")
	WebElement pendingMessage;

	private List<WebElement> trs;

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

	public int searchRowForFieldNameAndValue(String fieldName, String fieldValue) {
		trs = getDriver().findElements(By.xpath("//table//table/tbody/tr"));
		logger.info(String.format("expected: %s:%s", fieldName, fieldValue));
		for (int i = 0; i < trs.size(); i++) {
			List<WebElement> tds = trs.get(i).findElements(By.xpath("td"));
			logger.info(String.format("actual: %s:%s", tds.get(2).getText(),
					tds.get(3).findElement(By.xpath(".//input")).getAttribute("value")));
			if (tds.get(2).getText().equals(fieldName)
					&& tds.get(3).findElement(By.xpath(".//input")).getAttribute("value").equals(fieldValue)) {
				return i;
			}
		}
		return -1;
	}

	public void redact(int i) {
		WebElement tr = trs.get(i);
		List<WebElement> tds = trs.get(i).findElements(By.xpath("td"));
		tds.get(5).findElement(By.xpath(".//input[@value='Redact']/../div")).click();
	}

	public void edit(int i, String fieldValue) {
		WebElement tr = trs.get(i);
		List<WebElement> tds = trs.get(i).findElements(By.xpath("td"));
		WebElement fieldNameInput = tds.get(3).findElement(By.xpath(".//input"));
		fieldNameInput.clear();
		fieldNameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), fieldValue);
		tds.get(5).findElement(By.xpath(".//input[@value='Edit']/../div")).click();
	}

}