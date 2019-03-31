package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseData<T> {
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
