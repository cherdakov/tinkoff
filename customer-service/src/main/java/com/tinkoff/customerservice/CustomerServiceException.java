package com.tinkoff.customerservice;

import entity.ResponseData;
import entity.ResultCode;

public class CustomerServiceException extends Exception {

    ResultCode resultCode = ResultCode.UNDEFINED;

    public CustomerServiceException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public CustomerServiceException(String message, ResultCode resultCode) {
        super(message);
        this.resultCode = resultCode;
    }

    public CustomerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerServiceException(Throwable cause) {
        super(cause);
    }

    public CustomerServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
