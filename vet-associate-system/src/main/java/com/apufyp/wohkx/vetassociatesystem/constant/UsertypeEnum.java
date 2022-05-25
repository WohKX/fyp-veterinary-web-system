package com.apufyp.wohkx.vetassociatesystem.constant;

public enum UsertypeEnum {
	Pet_Owner("PO", "Pet_Owner", "Pet Owner"),
	Veterinarian("VE", "Vets", "Veterinarian"),
	Administrator("AD", "Admin", "Administrator");
	
	public final String code;
	public final String name;
	public final String description;
	
	
	private UsertypeEnum(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
