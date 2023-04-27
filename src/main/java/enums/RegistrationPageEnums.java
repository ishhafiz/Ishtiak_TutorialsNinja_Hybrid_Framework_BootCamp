package enums;

public enum RegistrationPageEnums {
	VALID_USERNAME_VALID_PASSWORD("Valid username and valid password."),
	INVALID_USERNAME_INVALID_PASSWORD("Invalid username and Invalid password."),
	REREGISTER_VALID_USERNAME_VALID_PASSWORD("Reregister valid username and valid password."),
	VALID_USERNAME_INVALID_CONF_PASSWORD("Valid username and invalid confirmation password."),
	REGISTRATION_EMPTY_FIELDS("Registration with empty fields"), 
	REG_EMPT_FNAME_LNAME_VALID_USER_PASS("Registration with empty first name and last name"),
	REG_EMPT_PHONE_EMPT_EMAIL("Registration with empty phone and email."),
	REG_WITHOUT_PVC_PLC_CHKBOX("Registration without privacy policy checkbox");
	
	
	public final String credentialValidity;

	private RegistrationPageEnums(String credentialValidity) {
		this.credentialValidity = credentialValidity;
	}
}
