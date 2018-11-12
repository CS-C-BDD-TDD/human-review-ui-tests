package gov.dhs.nppd.csc.nsd.yellowdog.util;

public class StixInfo {
	private String stixId;
	private String fieldName;
	private String fieldValue;
	private String acceptedValue;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAcceptedValue() {
		return acceptedValue;
	}

	public void setAcceptedValue(String acceptedValue) {
		this.acceptedValue = acceptedValue;
	}

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

}