package enums;

public enum LoginMessagesEnum {
	
	WRONG_CREDENTIAL_MESSAGE("Warning: No match for E-Mail Address and/or Password.");
	
	 public final String loginMessage;

	    private LoginMessagesEnum(String loginMessage) {
	        this.loginMessage = loginMessage;
	    }
}
