package com.apufyp.wohkx.vetassociatesystem.constant;

public enum TicketStatusEnum {
	New_Raised("NRA", "New Raised Ticket"), Reviewed("REV", "In Processing"), Completed("COM", "Completed (Accepted)"),
	Rejected("REJ", "Rejected");

	public final String code;
	public final String description;

	private TicketStatusEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static String getDescriptionMsg() {
		return (NewRaisedDesc() + ReviewedDesc() + CompletedDesc() + RejectedDesc());
	}

	private static final String NewRaisedDesc() {
		return New_Raised.getCode().concat("-").concat(New_Raised.getDescription()).concat("  ");
	}

	private static final String ReviewedDesc() {
		return Reviewed.getCode().concat("-").concat(Reviewed.getDescription()).concat("  ");
	}

	private static final String CompletedDesc() {
		return Completed.getCode().concat("-").concat(Completed.getDescription()).concat("  ");
	}

	private static final String RejectedDesc() {
		return Rejected.getCode().concat("-").concat(Rejected.getDescription()).concat("  ");
	}
}
