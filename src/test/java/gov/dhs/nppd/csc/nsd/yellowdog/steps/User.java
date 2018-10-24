package gov.dhs.nppd.csc.nsd.yellowdog.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import gov.dhs.nppd.csc.nsd.yellowdog.pages.HumanReviewPage;
import gov.dhs.nppd.csc.nsd.yellowdog.pages.LoginPage;

public class User extends ScenarioSteps {
	LoginPage loginPage;
	HumanReviewPage hrPage;

	private static Properties properties;
	private static String propertyFile = "src/test/resources/config.properties";

	public User() {
		try {
			loadProperties(propertyFile);
		} catch (FileNotFoundException e)
		{
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
	
//	@Step
//	public void should_see_action_called(String action) {
//		assertThat(loginPage.getActions()).contains(action);		
//	}
	
}