package gov.dhs.nppd.csc.nsd.yellowdog.pages;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;

public class HrPendingPage extends PageObject {

	@FindBy(xpath = "//div[@class='q-table-title']")
	private WebElement titleLable;

	public String getPageTitle() {
		return titleLable.getText();
	}

}