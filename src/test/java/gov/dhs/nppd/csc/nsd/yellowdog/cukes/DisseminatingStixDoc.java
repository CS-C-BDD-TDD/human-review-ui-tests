package gov.dhs.nppd.csc.nsd.yellowdog.cukes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.When;
import gov.dhs.nppd.csc.nsd.yellowdog.steps.User;
import gov.dhs.nppd.csc.nsd.yellowdog.util.CommonUtil;
import net.thucydides.core.annotations.Steps;

public class DisseminatingStixDoc {
	private static final Logger logger = LoggerFactory.getLogger(DisseminatingStixDoc.class);
	@Steps
	private User user;

	@When("^I disseminate the document$")
	public void i_disseminate_the_document() throws Exception {
		user.clicks_dissemination_button_for_the_document(CommonUtil.getStixId());
	}

}
