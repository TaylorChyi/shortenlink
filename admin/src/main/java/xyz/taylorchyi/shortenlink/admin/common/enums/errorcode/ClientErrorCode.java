package xyz.taylorchyi.shortenlink.admin.common.enums.errorcode;

import xyz.taylorchyi.shortenlink.admin.common.convention.errorcode.IErrorCode;

public enum ClientErrorCode implements IErrorCode {

    CLIENT_ERROR("A000001", "Client error occurred."),

    // User Registration Errors
    REGISTRATION_ERROR("A000100", "Registration failed."),
    USERNAME_VERIFICATION_ERROR("A000110", "Username verification failed."),
    USERNAME_ALREADY_EXISTS("A000111", "Username already exists."),
    USERNAME_CONTAINS_SENSITIVE_WORDS("A000112", "Username contains sensitive words."),
    USERNAME_CONTAINS_SPECIAL_CHARS("A000113", "Username contains special characters."),
    USER_DOES_NOT_EXIST("A000114", "User does not exist."),

    // Password Errors
    PASSWORD_VERIFICATION_FAILED("A000120", "Password verification failed."),
    PASSWORD_TOO_SHORT("A000121", "Password is too short."),

    // Phone Number Errors
    PHONE_FORMAT_INVALID("A000151", "Invalid phone number format."),

    // Idempotency Errors
    IDEMPOTENT_TOKEN_MISSING("A000200", "Idempotent token is missing."),
    IDEMPOTENT_TOKEN_INVALID("A000201", "Idempotent token is used or expired."),

    // User Login Errors
    USER_HAS_ALREADY_LOGGED_IN("A0003000", "User has already logged in."),
    USER_DID_NOT_LOG_IN("A0003001", "User did not log in."),
    USER_LOG_IN_SESSION_EXPIRED("A0003002", "User login session expired.");

    private final String code;
    private final String message;

    ClientErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
