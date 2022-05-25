package com.apufyp.wohkx.vetassociatesystem.constant;

public enum TicketTypeEnum {

	Pet_Owner_Inquiry("Pet Owner", "Inquiry - "), Veterinarian_New_Acc("Veterinarian", "Vet Account need Approval: "),
	Veterinarian_Inquiry("Veterinarian", "Inquiry - "), CH_Registration("Clinic/Hospital", "New Registration: "),
	CH_Change_Owner("Clinic/Hospital", "Change Owner for ");

	public final String raiser;
	public final String description;

	private TicketTypeEnum(String raiser, String description) {
		this.raiser = raiser;
		this.description = description;
	}

	public String getRaiser() {
		return raiser;
	}

	public String getDescription() {
		return description;
	}

}
