package gov.dhs.nppd.csc.nsd.yellowdog.util;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CukesConfig {
	private Logger logger = LoggerFactory.getLogger(CukesConfig.class);
	private static Configuration config = null;

	public static CukesConfig getInstance() {
		return new CukesConfig();
	}

	public CukesConfig() {
		if (config != null) {
			return;
		}

		logger.info("loading cukes config properties ...");
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(
				PropertiesConfiguration.class)
						.configure((params.properties().setFileName(System.getProperty("cukes.config.file"))));
		try {
			config = builder.getConfiguration();
		} catch (ConfigurationException e) {
			logger.error("Unable to get properties config: " + e.getMessage());
		}
	}

	public String getString(String key) {
		if (key.trim().isEmpty()) {
			return key.trim();
		}

		String variable = StringReplacement.getString(key.trim());

		if (key.equalsIgnoreCase(variable)) {
			if (System.getProperty(key) != null) {
				return System.getProperty(key);
			} else {
				if (config.getProperty(key) != null) {
					return (String) config.getProperty(key);
				}
				return key;
			}
		}

		return config.getString(variable);
	}

}