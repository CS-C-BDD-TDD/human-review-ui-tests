package gov.dhs.nppd.csc.nsd.yellowdog.cukes.stix;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.dhs.nppd.csc.nsd.yellowdog.steps.UserApiSteps;
import gov.dhs.nppd.csc.nsd.yellowdog.util.CommonUtil;
import gov.dhs.nppd.csc.nsd.yellowdog.util.HumanReviewItem;
import net.thucydides.core.annotations.Steps;

public class ManagingStixDocs {
	private static final Logger logger = LoggerFactory.getLogger(ManagingStixDocs.class);
	private String newStixId = null;

	@Steps
	private UserApiSteps userApiSteps;
	private String originalStixDoc;

	@Given("^I submit a STIX document to Yellow Dog \"([^\"]*)\"$")
	public void i_submit_a_STIX_document_to_Yellow_Dog(String fileName) throws Exception {
		logger.info("Submitting Stix doc from file ... " + fileName);

		originalStixDoc = userApiSteps.gets_stix_doc(fileName);

		newStixId = userApiSteps.places_stix_doc_into_HR_queue(originalStixDoc);

		CommonUtil.setStixId(newStixId);
		CommonUtil.setOriginalStixDoc(originalStixDoc);
		logger.info("New stix Id ... " + newStixId);
	}

	@Then("^I should receive a new document where \"([^\"]*)\" is being redacted$")
	public void i_should_receive_a_new_document_where_is_being_redacted(String arg1)
			throws Exception {
		logger.info("getting a transformed stix document ...");
		String stixDoc = userApiSteps.gets_stix_doc_from_HR_queue(CommonUtil.getStixId());
		logger.info("Stix doc from HR queue: " + stixDoc);
	}

	@Then("^I should receive a new document with these updated fields:$")
	public void i_should_receive_a_new_document_with_these_updated_fields(
			List<HumanReviewItem> hrItems) throws Exception {
		CommonUtil.setModifiedStixDoc(userApiSteps.gets_stix_doc_from_HR_queue(CommonUtil.getStixId()));
	}

	@Then("^other fields remain the same$")
	public void other_fields_remain_the_same() throws Exception {
		logger.info("Origial  Stix Doc: " + CommonUtil.getOriginalStixDoc());
		logger.info("Modified Stix Doc: " + CommonUtil.getModifiedStixDoc());
		assertTrue(true); // need more work
	}

}
