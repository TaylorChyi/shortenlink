package xyz.taylorchyi.shortenlink.admin.common.convention.exception;

import xyz.taylorchyi.shortenlink.admin.common.convention.errorcode.IErrorCode;
import xyz.taylorchyi.shortenlink.admin.common.enums.errorcode.RemoteErrorCode;

public class RemoteException extends AbstractException {

    public RemoteException(String message) {
        this(message, null, RemoteErrorCode.REMOTE_SERVICE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
