package com.apufyp.wohkx.vetassociatesystem.constant;

public enum AptStatusEnum {
	Request("NEW", "New Appointment Need Response"), Accepted("ACP", "Accepted Appointment"), Rejected("REJ", "Rejected Appointment"),
	Completed("COM", "Completed Appointment"), Expired("EXP", "Expired Appointment");

	public final String code;
	public final String description;

	private AptStatusEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
