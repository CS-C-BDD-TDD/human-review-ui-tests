package gov.dhs.nppd.csc.nsd.yellowdog.util;

public class CommonUtil {

	private static CukesConfig cukesConfig = CukesConfig.getInstance();

	public static String get(String key) {
		System.out.println(String.format("%s: %s", key, cukesConfig.getString(key)));
		return cukesConfig.getString(key);
	}
}
