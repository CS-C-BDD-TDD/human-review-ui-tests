package gov.dhs.nppd.csc.nsd.yellowdog.cukes;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.dhs.nppd.csc.nsd.yellowdog.steps.User;
import gov.dhs.nppd.csc.nsd.yellowdog.steps.UserSteps;
import gov.dhs.nppd.csc.nsd.yellowdog.util.CommonUtil;
import gov.dhs.nppd.csc.nsd.yellowdog.util.HumanReviewItem;
import net.thucydides.core.annotations.Steps;

public class RedactingFields {
	private static final Logger logger = LoggerFactory.getLogger(RedactingFields.class);
	@Steps
	private UserSteps userSteps;

	@Steps
	private User user;

	private int rowNumber4TargetedField;

	private List<Integer> rowNumbers;

	private void redactField(String fieldName) throws Exception {
		String stixId = CommonUtil.getStixId();

		user.select_display_all();

		rowNumber4TargetedField = user.searches_row_that_has(stixId, fieldName);

		if (rowNumber4TargetedField < 0) {
			throw new Exception(
					String.format("Unable to locate the row for the field name %s", fieldName));
		}
		user.found_row_that_has(rowNumber4TargetedField + 1, fieldName);
		user.redact_the_field(rowNumber4TargetedField + 1);
	}

	@Given("^I redact these fields and accept other fields as not PII:$")
	public void i_redact_these_fields_and_accept_other_fields_as_not_PII(
			List<HumanReviewItem> hrItems) throws Exception {
		hrItems.stream().forEach(hrItem -> {
			try {
				redactField(hrItem.getFieldName());
			} catch (Exception e) {
				logger.info("Error: " + e);
			}
		});
		notPiiOthers();
	}

	
	@When("^I readact the following fields:$")
	public void i_readact_the_following_fields(List<HumanReviewItem> hrItems) throws Exception {
		hrItems.stream().forEach(hrItem -> {
			try {
				redactField(hrItem.getFieldName());
			} catch (Exception e) {
				logger.info("Error: " + e);
			}
		});
	}

	private void notPiiOthers() {

		String stixId = CommonUtil.getStixId();

		user.select_display_all();
		rowNumbers = user.search_rows_with_new_status(stixId);
		for (Integer rowNumber : rowNumbers) {
			logger.info("row number: " + rowNumber);
			user.not_pii_the_field(rowNumber + 1, "");
		}

	}

}
