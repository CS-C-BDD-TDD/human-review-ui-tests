package gov.dhs.nppd.csc.nsd.yellowdog.cukes;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.dhs.nppd.csc.nsd.yellowdog.steps.UserSteps;
import gov.dhs.nppd.csc.nsd.yellowdog.util.CommonUtil;
import net.thucydides.core.annotations.Steps;

public class AuthenticatingUser {
	private String username;
	private String password;

	@Steps
	private UserSteps userSteps;

	@Given("^I am a regular user of the Yellow Dog Application with a valid credential$")
	public void i_am_a_regular_user_of_the_Yellow_Dog_Application_with_a_valid_credential() throws Exception {
		username = CommonUtil.get("hr.regular.username");
		password = CommonUtil.get("hr.regular.password");
	}

	@When("^I login to the website$")
	public void i_login_to_the_website() throws Exception {
		userSteps.i_visit_the_website(CommonUtil.get("hr.website.url"));
		userSteps.i_enter_user_name(username);
		userSteps.i_enter_password(password);
		userSteps.i_click_login_button();
	}

	@Then("^I should be presented with a list of pending human review items$")
	public void i_should_be_presented_with_a_list_of_pending_human_review_items() throws Exception {
		userSteps.i_verify_human_review_pending_page_with_this_title(CommonUtil.get("hr.pending.title"));
	}

	@Given("^I am a regular user of the Yellow Dog Application with an invalid credential as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_am_a_regular_user_of_the_Yellow_Dog_Application_with_an_invalid_credential_as_and(String username,
			String password) throws Exception {
		this.username = username;
		this.password = password;
	}

	@Then("^I should be denied access\\.$")
	public void i_should_be_denied_access() throws Exception {
		userSteps.i_verify_access_denial_with(CommonUtil.get("hr.login.error.msg"));
	}

}
