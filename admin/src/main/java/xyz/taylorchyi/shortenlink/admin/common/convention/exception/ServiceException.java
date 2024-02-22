package xyz.taylorchyi.shortenlink.admin.common.convention.exception;

import xyz.taylorchyi.shortenlink.admin.common.convention.errorcode.IErrorCode;
import xyz.taylorchyi.shortenlink.admin.common.enums.errorcode.ServiceErrorCode;

import java.util.Optional;

public class ServiceException extends AbstractException {

    public ServiceException(String message) {
        this(message, null, ServiceErrorCode.INTERNAL_SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.getMessage()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
