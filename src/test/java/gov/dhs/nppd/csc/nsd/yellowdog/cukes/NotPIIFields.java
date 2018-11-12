package gov.dhs.nppd.csc.nsd.yellowdog.cukes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import gov.dhs.nppd.csc.nsd.yellowdog.steps.User;
import gov.dhs.nppd.csc.nsd.yellowdog.util.CommonUtil;
import gov.dhs.nppd.csc.nsd.yellowdog.util.HumanReviewItem;
import net.thucydides.core.annotations.Steps;

public class NotPIIFields {
	private static final Logger logger = LoggerFactory.getLogger(NotPIIFields.class);

	@Steps
	private User user;
	private List<Integer> rowNumbers;

	private int rowNumber4TargetedField;

	@Given("^I accept others as Not PII$")
	public void i_accept_others_as_Not_PII() throws Exception {
		String stixId = CommonUtil.getStixId();

		user.select_display_all();
		rowNumbers = user.search_rows_with_new_status(stixId);
		for (Integer rowNumber : rowNumbers) {
			logger.info("row number: " + rowNumber);
			user.not_pii_the_field(rowNumber + 1, "");
		}
	}

	@When("^I Not PII the following fields:$")
	public void i_Not_PII_the_following_fields(List<HumanReviewItem> hrItems) throws Exception {
		hrItems.stream().forEach(hrItem -> {
			try {
				notPII(hrItem.getFieldName(), hrItem.getFieldValue());
			} catch (Exception e) {
				logger.info("Error: " + e);
			}
		});
	}

	private void notPII(String fieldName, String fieldValue) throws Exception {
		String stixId = CommonUtil.getStixId();

		user.select_display_all();

		rowNumber4TargetedField = user.searches_row_that_has(stixId, fieldName);

		if (rowNumber4TargetedField < 0) {
			throw new Exception(
					String.format("Unable to locate the row for the field name %s", fieldName));
		}
		user.found_row_that_has(rowNumber4TargetedField + 1, fieldName);
		user.not_pii_the_field(rowNumber4TargetedField + 1, fieldValue);
	}
}
