package gov.dhs.nppd.csc.nsd.yellowdog.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	private static Hashtable<String, Object> hashTable = new Hashtable<String, Object>();
	private static CukesConfig cukesConfig = CukesConfig.getInstance();

	public static String get(String key) {
		System.out.println(String.format("%s: %s", key, cukesConfig.getString(key)));
		return cukesConfig.getString(key);
	}

	private String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();

	}

	public String getDocument(String fileName) {
		Class<CommonUtil> clazz = CommonUtil.class;
		InputStream inputStream = clazz
				.getResourceAsStream("/stix_docs/identifying_threat_actor_profile.json");

		try {
			return readFromInputStream(inputStream);
		} catch (IOException e) {
			logger.info("Error: " + e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.info("Error: " + e);
				}
			}
		}
		return "";
	}

	public static void setStixId(String stixId) {
		hashTable.put("stix_id", stixId);
	}

	public static String getStixId() {
		return (String) hashTable.get("stix_id");
	}

	public static void set(String key, String value) {
		hashTable.put(key, value);
	}

	public static void setUsername(String username) {
		hashTable.put("username", username);
	}

	public static void setPassword(String password) {
		hashTable.put("password", password);
	}

	public static String getUsername() {
		return (String) hashTable.get("username");
	}

	public static String getPassword() {
		return (String) hashTable.get("password");
	}

	public static void setModifiedStixDoc(String modifiedStixDoc) {
		hashTable.put("modified_stix_doc", modifiedStixDoc);
	}

	public static void setOriginalStixDoc(String originalStixDoc) {
		hashTable.put("original_stix_doc", originalStixDoc);
	}

	public static String getOriginalStixDoc() {
		return (String) hashTable.get("original_stix_doc");
	}

	public static String getModifiedStixDoc() {
		return (String) hashTable.get("modified_stix_doc");
	}
}
