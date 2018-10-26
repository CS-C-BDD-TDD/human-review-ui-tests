package gov.dhs.nppd.csc.nsd.yellowdog.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import gov.dhs.nppd.csc.nsd.yellowdog.pages.HrPendingPage;
import gov.dhs.nppd.csc.nsd.yellowdog.pages.YellowDogPage;
import net.thucydides.core.annotations.Step;

public class UserSteps {

	private YellowDogPage yellowDogPage;
	private HrPendingPage hrPendingPage;

	@Step
	public void i_visit_the_website(String websiteUrl) {
		yellowDogPage.visit(websiteUrl);
	}

	@Step
	public void i_enter_user_name(String username) {
		yellowDogPage.enterUserName(username);
	}

	@Step
	public void i_enter_password(String password) {
		yellowDogPage.enterPassword(password);
	}

	@Step
	public void i_click_login_button() {
		yellowDogPage.clickLoginButton();
	}

	@Step
	public void i_verify_human_review_pending_page_with_this_title(String expectedTitleText) {
		assertThat(hrPendingPage.getPageTitle(), equalTo(expectedTitleText));
	}

	@Step
	public void i_verify_access_denial_with(String expectedError) {
		assertThat(yellowDogPage.getLoginDenialMsg(), equalTo(expectedError));
	}

}
