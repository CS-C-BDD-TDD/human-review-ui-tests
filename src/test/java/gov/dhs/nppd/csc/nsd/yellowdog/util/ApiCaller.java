package gov.dhs.nppd.csc.nsd.yellowdog.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiCaller {
	private static final String HUMANREVIEW_STIXDOC = "/humanreview/stixdoc";
	private static final Logger logger = LoggerFactory.getLogger(ApiCaller.class);
	private RestTemplate restTemplate = new RestTemplate();

	// hr.restapi.url
	public String postStixDoc(String stixDoc) {
		logger.info("postStixDoc: " + stixDoc);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(stixDoc, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(geApitUrl(), request,
				String.class);
		return response.getBody();
	}

	private String geApitUrl() {
		return CommonUtil.get("hr.partnerapi.url") + HUMANREVIEW_STIXDOC;
	}

	public String getStixDoc() {
		logger.info("getStixDoc: ");
		ResponseEntity<String> response = restTemplate.getForEntity(geApitUrl(), String.class);
		return response.getBody();
	}

	private String getUserUrl() {
		return CommonUtil.get("hr.restapi.url") + "/user";
	}

	private String getPendingUrl() {
		return CommonUtil.get("hr.restapi.url") + "/humanreview/pending";
	}

	public String getAuthorized(String username, String password) {
		logger.info("username: " + username);
		logger.info("password: " + password);
		HttpHeaders headers = new HttpHeaders();
		AuthCredentials authCredentials = new AuthCredentials();
		authCredentials.setUsername(username);
		authCredentials.setPassword(password);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AuthCredentials> request = new HttpEntity<AuthCredentials>(authCredentials,
				headers);
		ResponseEntity<String> response = restTemplate.exchange(getUserUrl(), HttpMethod.PUT,
				request, String.class);
		return response.getBody();
	}

	public List<HumanReviewItem> getPending(String token) {
		logger.info("getPending: " + token);
		HttpHeaders headers = new HttpHeaders();
		headers.set("token", token);
		HttpEntity<String> request = new HttpEntity<String>("", headers);
		ResponseEntity<List> response = restTemplate.exchange(getPendingUrl(), HttpMethod.GET,
				request, List.class);
		return (List<HumanReviewItem>) response.getBody();
	}
}
