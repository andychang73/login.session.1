package com.example.abstractionizer.login.session1.responses;

import com.example.abstractionizer.login.session1.enums.BaseError;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse<T> {
    private String code;
    private String msg;
    private T details;

    public ErrorResponse(BaseError baseError, T details){
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        this.details = details;
    }
}
