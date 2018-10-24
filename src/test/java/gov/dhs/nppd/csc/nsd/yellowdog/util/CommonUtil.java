package gov.dhs.nppd.csc.nsd.yellowdog.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtil {

	private static Properties properties;
	private static String propertyFile = "src/test/resources/config.properties";

	static {
		try {
			loadProperties(propertyFile);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	private static void loadProperties(String file) throws FileNotFoundException {
		properties = new Properties();
		InputStream is = new FileInputStream(file);

		try {
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Properties getSystemProperties() {
		return properties;
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}
}
