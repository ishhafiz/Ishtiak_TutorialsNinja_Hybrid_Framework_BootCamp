package enums;

public enum TestdataValidityEnum {
	VALID_USERNAME_VALID_PASSWORD("Valid Username and Valid Password"),
	INVALID_USERNAME_INVALID_PASSWORD("Invalid Username and Invalid Password"),
	VALID_USERNAME_INVALID_PASSWORD("Valid Username and Invalid Password"),
	INVALID_USERNAME_VALID_PASSWORD("Invalid Username and valid Password"),
	VALID_USERNAME_EMPTY_PASSWORD("Valid Username and Empty Password"),
	EMPTY_USERNAME_VALID_PASSWORD("Empty Username and Valid Password"),
	EMPTY_USERNAME_EMPTY_PASSWORD("Empty Username and EMPTY Password");

	public final String testdataValidity;

    private TestdataValidityEnum(String testdataValidity) {
        this.testdataValidity = testdataValidity;
    }
	
}
