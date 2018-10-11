package gov.dhs.nppd.csc.nsd.yellowdog.features.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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
		//user.verify_login_fail();
	}
}