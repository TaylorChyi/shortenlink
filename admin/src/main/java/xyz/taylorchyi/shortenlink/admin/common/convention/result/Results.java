package xyz.taylorchyi.shortenlink.admin.common.convention.result;

import xyz.taylorchyi.shortenlink.admin.common.convention.exception.AbstractException;
import xyz.taylorchyi.shortenlink.admin.common.enums.errorcode.ServiceErrorCode;

import java.util.Optional;

public final class Results {

    public static Result<Void> success() {
        return new Result<Void>()
                .setResponseCode(Result.SUCCESS_CODE);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>()
                .setResponseCode(Result.SUCCESS_CODE)
                .setResponseData(data);
    }

    public static Result<Void> serviceFailure() {
        return new Result<Void>()
                .setResponseCode(ServiceErrorCode.INTERNAL_SERVICE_ERROR.getCode())
                .setResponseMessage(ServiceErrorCode.INTERNAL_SERVICE_ERROR.getMessage());
    }

    public static Result<Void> serviceFailure(AbstractException abstractException) {
        String errorCode = Optional.ofNullable(abstractException.getErrorCode())
                .orElse(ServiceErrorCode.INTERNAL_SERVICE_ERROR.getCode());
        String errorMessage = Optional.ofNullable(abstractException.getErrorMessage())
                .orElse(ServiceErrorCode.INTERNAL_SERVICE_ERROR.getMessage());
        return new Result<Void>()
                .setResponseCode(errorCode)
                .setResponseMessage(errorMessage);
    }

    public static Result<Void> failure(String errorCode, String errorMessage) {
        return new Result<Void>()
                .setResponseCode(errorCode)
                .setResponseMessage(errorMessage);
    }
}