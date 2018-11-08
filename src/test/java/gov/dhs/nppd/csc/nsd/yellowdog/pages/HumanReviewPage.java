package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.dhs.nppd.csc.nsd.yellowdog.util.StixInfo;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class HumanReviewPage extends PageObject {
	private static final int ACTION_COLUMN = 5;
	private static final String REDACT_TEMPLATE = ".//input[@value='Redact']/../div";
	private static final String EDIT_TEMPLATE = ".//input[@value='Edit']/../div";
	private static final String NOT_PII_TEMPLATE = ".//input[@value='Not PII']/../div";
	private static final String CONFIRM_RISK_TEMPLATE = ".//input[@value='Confirm Risk']/../div";
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

	public int getNumberOfFields(String stixId) {
		String xpath = String.format("//td[text()='%s']", stixId);
		return getDriver().findElements(By.xpath(xpath)).size();
	}

	private List<WebElement> tds = null;

	public int searchRowForFieldNameAndValue(StixInfo stixInfo) {
		String xpath = String.format("//td[contains(text(),'%s')]", stixInfo.getStixId());
		tds = getDriver().findElements(By.xpath(xpath));
		logger.info(String.format("expected: %s:%s", stixInfo.getFieldName(), stixInfo.getFieldValue()));

		for (int i = 0; i < tds.size(); i++) {
			WebElement td = tds.get(i);
			WebElement fieldNameEl = td.findElement(By.xpath("../td[4]"));
			WebElement fieldValueEl = td.findElement(By.xpath("../td[5]"));
			logger.info(String.format("actual: %s:%s", fieldNameEl.getText(), fieldValueEl.getText()));
			if (fieldNameEl.getText().equals(stixInfo.getFieldName())
					&& fieldValueEl.getText().equals(stixInfo.getFieldValue())) {
				return i;
			}
		}
		return -1;
	}

	public void redact(int rowNo) {
		WebElement td = tds.get(rowNo);
		WebElement actionSelector = td.findElement(By.xpath("../td[7]//i"));
		actionSelector.click();
		getDriver().findElement(By.xpath("//div[@tabindex='-1']//div[contains(text(),'Redact')]")).click();
	}

	public void edit(int rowNo, String fieldValue) {
		WebElement td = tds.get(rowNo);
		td.findElement(By.xpath("../td[5]")).click();
		this.delay(1000L);
		WebElement inputEl = getDriver().findElement(By.xpath("//div[@tabindex='-1']//input"));
		inputEl.clear();
		inputEl.sendKeys(fieldValue);
		getDriver().findElement(By.xpath("//div[@tabindex='-1']//div[text()='Set']")).click();
	}

	private void delay(long delayInMs) {
		try {
			Thread.sleep(delayInMs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void takeAction(int rowNo, String fieldValue, String actionTemplate) {
		WebElement tr = trs.get(rowNo);
		List<WebElement> tds = trs.get(rowNo).findElements(By.xpath("td"));
		WebElement fieldNameInput = tds.get(3).findElement(By.xpath(".//input"));
		fieldNameInput.clear();
		fieldNameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), fieldValue);
		tds.get(ACTION_COLUMN).findElement(By.xpath(actionTemplate)).click();
	}

	public void notPii(int rowNo, String acceptedValue) {
		WebElement td = tds.get(rowNo);
		WebElement actionSelector = td.findElement(By.xpath("../td[7]//i"));
		actionSelector.click();
		getDriver().findElement(By.xpath("//div[@tabindex='-1']//div[contains(text(),'Not PII')]")).click();
	}

	public void confirmRisk(int rowNo, String acceptedValue) {
		WebElement td = tds.get(rowNo);
		WebElement actionSelector = td.findElement(By.xpath("../td[7]//i"));
		actionSelector.click();
		getDriver().findElement(By.xpath("//div[@tabindex='-1']//div[contains(text(),'Confirm Risk')]")).click();
	}

	@FindBy(xpath = "//span[text()='Records per page:']/..//i")
	private WebElement optionsSelector;

	@FindBy(xpath = "//div[contains(text(),'All')]")
	private WebElement selectDisplayAll;

	public void selectDisplayAll() {
		optionsSelector.click();
		selectDisplayAll.click();
	}

	@FindBy(xpath = "//td[text()='4fa1581e-74a5-40d7-a7f2-8b3424983de4']/../td[7]//i")
	private WebElement actionsSelector;

	public String getHrFieldStatus(int rowNo) {
		return tds.get(rowNo).findElement(By.xpath("../td[6]")).getText();
	}
}