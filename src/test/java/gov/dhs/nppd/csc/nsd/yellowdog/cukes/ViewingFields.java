package gov.dhs.nppd.csc.nsd.yellowdog.cukes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.dhs.nppd.csc.nsd.yellowdog.steps.User;
import gov.dhs.nppd.csc.nsd.yellowdog.util.CommonUtil;
import gov.dhs.nppd.csc.nsd.yellowdog.util.HumanReviewItem;
import net.thucydides.core.annotations.Steps;

public class ViewingFields {
	private static final Logger logger = LoggerFactory.getLogger(ViewingFields.class);

	@Steps
	private User user;

	private int numberOfFields;

	@When("^I look for all human review fields for a STIX document$")
	public void i_look_for_all_human_review_fields_for_a_STIX_document() throws Exception {
		user.select_display_all();
		numberOfFields = user.gets_hr_fields(CommonUtil.getStixId()).size();
	}

	@Then("^I should see (\\d+) fields that need human review$")
	public void i_should_see_fields_that_need_human_review(int expectedNumberOfFields) throws Exception {
		assertThat(numberOfFields, equalTo(expectedNumberOfFields));
	}

	@Then("^the modified fields should be as follows:$")
	public void the_modified_fields_should_be_as_follows(List<HumanReviewItem> expectedHrItems) throws Exception {
		List<HumanReviewItem> actualHrItems = user.gets_hr_fields(CommonUtil.getStixId());
		boolean found = true;

		for (HumanReviewItem expected : expectedHrItems) {
			logger.info("hrItem: " + expected);
			if (actualHrItems.stream().filter(actual -> isEqual(actual, expected)).collect(Collectors.toList())
					.isEmpty()) {
				found = false;
				break;
			}
		}

		assertThat(found, equalTo(true));
	}

	private Boolean isEqual(HumanReviewItem actual, HumanReviewItem expected) {
		return actual.getFieldName().equals(expected.getFieldName()) && actual.getStatus().equals(expected.getStatus())
				&& actual.getFieldValue().equals(expected.getFieldValue());
	}

}
