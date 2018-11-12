package gov.dhs.nppd.csc.nsd.yellowdog.cukes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.When;
import gov.dhs.nppd.csc.nsd.yellowdog.steps.User;
import gov.dhs.nppd.csc.nsd.yellowdog.util.CommonUtil;
import gov.dhs.nppd.csc.nsd.yellowdog.util.HumanReviewItem;
import net.thucydides.core.annotations.Steps;

public class ConfirmingRiskFields {
	private static final Logger logger = LoggerFactory.getLogger(ConfirmingRiskFields.class);

	@Steps
	private User user;

	private int rowNumber4TargetedField;

	@When("^I Confirm Risk the following fields:$")
	public void i_Confirm_Risk_the_following_fields(List<HumanReviewItem> hrItems)
			throws Exception {
		hrItems.stream().forEach(hrItem -> {
			try {
				confirmRisk(hrItem.getFieldName(), hrItem.getFieldValue());
			} catch (Exception e) {
				logger.info("Error: " + e);
			}
		});
	}

	private void confirmRisk(String fieldName, String fieldValue) throws Exception {
		String stixId = CommonUtil.getStixId();

		user.select_display_all();

		rowNumber4TargetedField = user.searches_row_that_has(stixId, fieldName);

		if (rowNumber4TargetedField < 0) {
			throw new Exception(
					String.format("Unable to locate the row for the field name %s", fieldName));
		}
		user.found_row_that_has(rowNumber4TargetedField + 1, fieldName);
		user.confirm_risk_the_field(rowNumber4TargetedField + 1, fieldValue);
	}
}
