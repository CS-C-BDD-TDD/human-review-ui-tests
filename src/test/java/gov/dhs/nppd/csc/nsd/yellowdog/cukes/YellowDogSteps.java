package gov.dhs.nppd.csc.nsd.yellowdog.cukes;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.dhs.nppd.csc.nsd.yellowdog.steps.User;
import net.thucydides.core.annotations.Steps;

public class YellowDogSteps {
	@Steps
	User user;

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

	@Then("^I should see a list of (\\d+) fields that need human review$")
	public void i_should_see_a_list_of_fields_that_need_human_review(int expectedNumFields) throws Exception {
		user.verify_number_of_expected_fields(expectedNumFields);
	}

}