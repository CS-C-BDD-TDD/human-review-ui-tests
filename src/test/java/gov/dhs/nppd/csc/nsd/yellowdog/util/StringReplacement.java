package gov.dhs.nppd.csc.nsd.yellowdog.util;

public class StringReplacement {
	public static String getString(String variable) {
		if (variable.startsWith("${") && variable.endsWith("}")) {
			return variable.substring(2, variable.length() - 1);
		}
		return variable;
	}

	public static String removeXMLLabelsFromString(String XMLLine, String XMLLabel) {
		if (XMLLine.contains(XMLLabel)) {
			String EndLabel = "</" + XMLLine.substring(XMLLine.indexOf(XMLLabel) + 1, XMLLine.indexOf(">") + 1);
			return XMLLine.substring(XMLLabel.length() + 2, XMLLine.indexOf(EndLabel));
		}
		return XMLLine;
	}
}