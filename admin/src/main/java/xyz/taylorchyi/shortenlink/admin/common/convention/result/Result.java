package xyz.taylorchyi.shortenlink.admin.common.convention.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5679018624309023727L;

    public static final String SUCCESS_CODE = "0";

    private String responseCode;

    private String responseMessage;

    private T responseData;

    private String requestId;

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(responseCode);
    }
}