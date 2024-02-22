package xyz.taylorchyi.shortenlink.admin.common.enums.errorcode;

import xyz.taylorchyi.shortenlink.admin.common.convention.errorcode.IErrorCode;

public enum RemoteErrorCode implements IErrorCode {

    REMOTE_SERVICE_ERROR("C000001", "Error calling external service.");

    private final String code;
    private final String message;

    RemoteErrorCode(String code, String message) {
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
