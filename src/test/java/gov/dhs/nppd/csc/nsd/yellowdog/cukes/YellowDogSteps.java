package gov.dhs.nppd.csc.nsd.yellowdog.cukes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.dhs.nppd.csc.nsd.yellowdog.steps.User;
import gov.dhs.nppd.csc.nsd.yellowdog.util.StixInfo;
import net.thucydides.core.annotations.Steps;

public class YellowDogSteps {
	@Steps
	User user;
	private int rowNumber4TargetedField;
	private StixInfo originalStixInfo;
	private boolean editingField = false;
	private String stixId;

	@Given("^user enters \"([^\"]*)\" as username and \"([^\"]*)\" as password to sign in$")
	public void navigate_to_application_and_signin(String username, String password) throws Throwable {
		user.open_application();
		user.perform_sign_in(username, password);
	}

	@Then("^login should be successful$")
	public void verify_login_success() throws Throwable {
		user.verify_login_suceed();
	}

	@Then("^login should be unsuccessful$")
	public void verify_login_failure() throws Throwable {
		user.verify_login_fail();
	}

	@When("^I choose a STIX document \"([^\"]*)\"$")
	public void i_choose_a_STIX_document(String stixId) throws Exception {
		user.click_on_stix_id_entry(stixId);
	}

	@When("^I look for all human review fields for a STIX document \"([^\"]*)\"$")
	public void i_look_for_all_human_review_fields_for_a_STIX_document(String stixId) throws Exception {
		this.stixId = stixId;
		user.select_display_all();
	}

	@Then("^I should see a list of (\\d+) fields that need human review$")
	public void i_should_see_a_list_of_fields_that_need_human_review(int expectedNumFields) throws Exception {
		user.verify_number_of_expected_fields(stixId, expectedNumFields);
	}

	@When("^I readact a field with the following information:$")
	public void i_readact_a_field_with_the_following_information(List<StixInfo> stixInfoList) throws Exception {
		originalStixInfo = stixInfoList.get(0);
		user.select_display_all();

		rowNumber4TargetedField = user.searches_row_that_has(originalStixInfo);

		if (rowNumber4TargetedField < 0) {
			throw new Exception(String.format("Unable to locate the row for the field name/value %s:%s",
					originalStixInfo.getFieldName(), originalStixInfo.getFieldValue()));
		}
		user.found_row_that_has(rowNumber4TargetedField + 1, originalStixInfo.getFieldName(),
				originalStixInfo.getFieldValue());
		user.redact_the_field(rowNumber4TargetedField + 1);
	}

	@When("^I Not PII a field with the following information:$")
	public void i_Not_PII_a_field_with_the_following_information(List<StixInfo> stixInfoList) throws Exception {
		originalStixInfo = stixInfoList.get(0);
		user.select_display_all();

		rowNumber4TargetedField = user.searches_row_that_has(originalStixInfo);

		if (rowNumber4TargetedField < 0) {
			throw new Exception(String.format("Unable to locate the row for the field name/value %s:%s",
					originalStixInfo.getFieldName(), originalStixInfo.getFieldValue()));
		}
		user.found_row_that_has(rowNumber4TargetedField + 1, originalStixInfo.getFieldName(),
				originalStixInfo.getFieldValue());

		user.not_pii_the_field(rowNumber4TargetedField + 1, originalStixInfo.getAcceptedValue());
	}

	@When("^I Confirm Risk a field with the following information:$")
	public void i_Confirm_Risk_a_field_with_the_following_information(List<StixInfo> stixInfoList) throws Exception {
		originalStixInfo = stixInfoList.get(0);
		user.select_display_all();

		rowNumber4TargetedField = user.searches_row_that_has(originalStixInfo);

		if (rowNumber4TargetedField < 0) {
			throw new Exception(String.format("Unable to locate the row for the field name/value %s:%s",
					originalStixInfo.getFieldName(), originalStixInfo.getFieldValue()));
		}
		user.found_row_that_has(rowNumber4TargetedField + 1, originalStixInfo.getFieldName(),
				originalStixInfo.getFieldValue());

		user.confirm_risk_the_field(rowNumber4TargetedField + 1, originalStixInfo.getAcceptedValue());
	}

	@When("^I Edit a field with the following information:$")
	public void i_Edit_a_field_with_the_following_information(List<StixInfo> stixInfoList) throws Exception {
		originalStixInfo = stixInfoList.get(0);
		user.select_display_all();

		rowNumber4TargetedField = user.searches_row_that_has(originalStixInfo);

		if (rowNumber4TargetedField < 0) {
			throw new Exception(String.format("Unable to locate the row for the field name/value %s:%s",
					originalStixInfo.getFieldName(), originalStixInfo.getFieldValue()));
		}
		user.found_row_that_has(rowNumber4TargetedField + 1, originalStixInfo.getFieldName(),
				originalStixInfo.getFieldValue());

		user.edit_the_field(rowNumber4TargetedField + 1, originalStixInfo.getAcceptedValue());
		editingField = true;
	}

	@Then("^I should have the field displayed as follows:$")
	public void i_should_have_the_field_displayed_as_follows(List<StixInfo> expectedStixInfoList) throws Exception {
		StixInfo stixInfo = expectedStixInfoList.get(0);
		Thread.sleep(2000); // give 1 sec for update

		String actualStatus = null;
		int actualRowNo = user.searches_row_that_has(stixInfo);
		if (actualRowNo >= 0) {
			actualStatus = user.gets_human_review_field_status(actualRowNo);
		}
		user.reverse_change();

		user.edit_the_field(rowNumber4TargetedField + 1, originalStixInfo.getFieldValue());

		assertThat(actualRowNo, equalTo(rowNumber4TargetedField));
		assertThat(actualStatus, equalTo(stixInfo.getStatus()));

//		assertTrue(String.format("Unable to locate the row for the field name/value %s:%s", stixInfo.getFieldName(),
//				stixInfo.getFieldValue()), rowNumber4TargetedField >= 0);

	}

}