package gov.dhs.nppd.csc.nsd.yellowdog.util;

public class StixInfo {
	private String stixId;
	private String fieldName;

	public String getStixId() {
		return stixId;
	}

	public void setStixId(String stixId) {
		this.stixId = stixId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	private String fieldValue;
}
