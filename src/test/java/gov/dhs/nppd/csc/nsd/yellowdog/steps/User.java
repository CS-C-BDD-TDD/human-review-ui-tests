package gov.dhs.nppd.csc.nsd.yellowdog.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import gov.dhs.nppd.csc.nsd.yellowdog.pages.HumanReviewPage;
import gov.dhs.nppd.csc.nsd.yellowdog.pages.LoginPage;
import gov.dhs.nppd.csc.nsd.yellowdog.util.StixInfo;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class User extends ScenarioSteps {
	LoginPage loginPage;
	HumanReviewPage hrPage;

	private static Properties properties;
	private static String propertyFile = "src/test/resources/config.properties";

	public User() {
		try {
			loadProperties(propertyFile);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	private void loadProperties(String file) throws FileNotFoundException {
		properties = new Properties();
		InputStream is = new FileInputStream(file);

		try {
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Properties getSystemProperties() {
		return properties;
	}

	@Step
	public void open_application() {
		loginPage.open();
	}

	@Step
	public void perform_sign_in(String username, String password) {
		loginPage.signin(username, password);
	}

	@Step
	public void verify_login_suceed() {
		hrPage.check_for_pending_message();
	}

	@Step
	public void verify_login_fail() {
		loginPage.check_for_error_message();
	}

	@Step
	public void click_on_stix_id_entry(String stixId) {
		hrPage.click_stix_id(stixId);
	}

	@Step
	public void verify_number_of_expected_fields(String stixId, int expectedNumFields) {
		int actualNumFields = hrPage.getNumberOfFields(stixId);
		assertThat(actualNumFields, equalTo(expectedNumFields));
	}

	@Step
	public int searches_row_that_has(StixInfo stixInfo) {
		return hrPage.searchRowForFieldNameAndValue(stixInfo);
	}

	@Step
	public void found_row_that_has(int rowNumber4TargetedField, String fieldName, String fieldValue) {
		// for readability only
	}

	@Step
	public void redact_the_field(int rowNumberForTargetedField) {
		hrPage.redact(rowNumberForTargetedField - 1);
	}

	@Step
	public void edit_the_field(int rowNumberForTargetedField, String fieldValue) {
		hrPage.edit(rowNumberForTargetedField - 1, fieldValue);
	}

	@Step
	public void not_pii_the_field(int rowNumberForTargetedField, String acceptedValue) {
		hrPage.notPii(rowNumberForTargetedField - 1, acceptedValue);
	}

	@Step
	public void confirm_risk_the_field(int rowNumberForTargetedField, String acceptedValue) {
		hrPage.confirmRisk(rowNumberForTargetedField - 1, acceptedValue);
	}

	@Step
	public void reverse_change() {
		// Do nothing.
	}

	@Step
	public void select_display_all() {
		hrPage.selectDisplayAll();
	}

	@Step
	public String gets_human_review_field_status(int rowNo) {
		return hrPage.getHrFieldStatus(rowNo);
	}


}