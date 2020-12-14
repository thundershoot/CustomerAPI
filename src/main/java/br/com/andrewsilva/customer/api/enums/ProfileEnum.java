package br.com.andrewsilva.customer.api.enums;

public enum ProfileEnum {

	ROLE_ADMIN;

	public static ProfileEnum getProfile(String role) {
		switch (role) {
		case "ADMIN":
			return ROLE_ADMIN;
		default:
			return null;
		}
	}

}
