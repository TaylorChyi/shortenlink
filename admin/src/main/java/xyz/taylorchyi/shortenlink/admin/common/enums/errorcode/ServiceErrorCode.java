package xyz.taylorchyi.shortenlink.admin.common.enums.errorcode;

import xyz.taylorchyi.shortenlink.admin.common.convention.errorcode.IErrorCode;

public enum ServiceErrorCode implements IErrorCode {

    INTERNAL_SERVICE_ERROR("B000001", "Internal service error occurred."),
    SERVICE_TIMEOUT("B000100", "Service execution timed out.");

    private final String code;
    private final String message;

    ServiceErrorCode(String code, String message) {
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
