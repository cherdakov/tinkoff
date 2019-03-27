package entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResponseData<T> {
    enum ResultCode{
        OK,
        ERROR
    }
    ResultCode resultCode;
    T data;
    String errorMessage;
}
