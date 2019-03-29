package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ResponseData<T> {
    public enum ResultCode{
        OK,
        ERROR
    }
    ResultCode resultCode;
    T data;
    String errorMessage;

    public ResponseData(T data, ResultCode resultCode) {
        this.resultCode = resultCode;
        this.data = data;
    }

    public ResponseData(ResultCode resultCode, String errorMessage) {
        this.resultCode = resultCode;
        this.errorMessage = errorMessage;
    }
}
