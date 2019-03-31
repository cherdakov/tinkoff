package com.tinkoff.customerservice.controller;

import com.tinkoff.customerservice.CustomerServiceException;
import entity.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalHandler {
    @ExceptionHandler(CustomerServiceException.class)
    @ResponseBody
    ResponseData handleException(CustomerServiceException e){
        log.error("", e);
        return new ResponseData<>(ResponseData.ResultCode.ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseData handleException(Exception e){
        log.error("", e);
        return new ResponseData<>(ResponseData.ResultCode.ERROR, e.getMessage());
    }
}
