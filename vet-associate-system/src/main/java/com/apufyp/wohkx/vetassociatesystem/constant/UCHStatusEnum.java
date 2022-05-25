package com.apufyp.wohkx.vetassociatesystem.constant;

public enum UCHStatusEnum {
	Activated("ACTIVE", "Activated"), Blocked("BLOCK", "Blocked"), Processing("PROCESS", "In Processing");

	public final String code;
	public final String description;

	private UCHStatusEnum(String code, String name) {
		this.code = code;
		this.description = name;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
