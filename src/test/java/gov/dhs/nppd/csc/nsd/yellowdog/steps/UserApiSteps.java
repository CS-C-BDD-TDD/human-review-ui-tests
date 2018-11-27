package gov.dhs.nppd.csc.nsd.yellowdog.steps;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.dhs.nppd.csc.nsd.yellowdog.util.ApiCaller;
import gov.dhs.nppd.csc.nsd.yellowdog.util.CommonUtil;
import gov.dhs.nppd.csc.nsd.yellowdog.util.HumanReviewItem;
import net.thucydides.core.annotations.Step;

public class UserApiSteps {
	private static final Logger logger = LoggerFactory.getLogger(UserApiSteps.class);
	private ApiCaller apiCaller = new ApiCaller();

	@Step
	public String gets_stix_doc(String fileName) {
		return new CommonUtil().getDocument(fileName);
	}

	@Step
	public String places_stix_doc_into_HR_queue(String stixDoc) {
		return apiCaller.postStixDoc(stixDoc);
	}

	@Step
	public String authenticates_with_Yellow_Dog() {
		return apiCaller.getAuthorized(CommonUtil.get("hr.regular.username"),
				CommonUtil.get("hr.regular.password"));
	}

	@Step
	public List<HumanReviewItem> gets_pending_HR_items(String token) {
		return apiCaller.getPending(token);
	}

	public String gets_stix_doc_from_HR_queue(String stixId) {
		return apiCaller.getStixDoc();
	}

}
