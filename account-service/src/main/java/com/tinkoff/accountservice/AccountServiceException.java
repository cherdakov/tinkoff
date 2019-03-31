package com.tinkoff.accountservice;

import entity.ResultCode;

public class AccountServiceException extends Exception {

    ResultCode resultCode = ResultCode.UNDEFINED;

    public AccountServiceException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public AccountServiceException(String message, ResultCode resultCode) {
        super(message);
        this.resultCode = resultCode;
    }

    public AccountServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountServiceException(Throwable cause) {
        super(cause);
    }

    public AccountServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
