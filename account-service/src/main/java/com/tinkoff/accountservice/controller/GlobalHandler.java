package com.tinkoff.accountservice.controller;

import com.tinkoff.accountservice.AccountServiceException;
import entity.ResponseData;
import entity.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class GlobalHandler {
    @ExceptionHandler(AccountServiceException.class)
    @ResponseBody
    ResponseData handleException(AccountServiceException e) {
        log.error("", e);
        return new ResponseData<>(ResultCode.ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseData handleException(Exception e) {
        log.error("", e);
        return new ResponseData<>(ResultCode.ERROR, e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    @ResponseBody
    ResponseData handleException(IOException e) {
        log.error("", e);
        return new ResponseData<>(ResultCode.ERROR,
                "Something wrong with inner http: " + e.getMessage());
    }
}
