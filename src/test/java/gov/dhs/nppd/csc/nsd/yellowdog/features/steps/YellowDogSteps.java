package gov.dhs.nppd.csc.nsd.yellowdog.features.steps;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.dhs.nppd.csc.nsd.yellowdog.steps.User;
import net.thucydides.core.annotations.Steps;

public class YellowDogSteps {
	@Steps
	User user;

	private String username;
	private String password;

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

	/** Kiet's approach ***/

	@Given("^I am have a valid credential as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_am_have_a_valid_credential_as_and(String username, String password) throws Exception {
		this.username = username;
		this.password = password;
	}

	@When("^I access the application with that credentials$")
	public void i_access_the_application_with_that_credentials() throws Exception {
		user.open_application();
		user.i_enter_username(username);
		user.i_enter_password(password);
		user.i_click_on_signin_button();
	}

	@Then("^I should get access to the Human Review application$")
	public void i_should_get_access_to_the_Human_Review_application() throws Exception {
		String actualWelcomeMsg = user.i_get_welcome_message();
		assertThat(actualWelcomeMsg, containsString("Welcome to Human Review Pending"));
	}

}