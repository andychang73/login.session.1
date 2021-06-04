package com.example.abstractionizer.login.session1.enums;

import org.springframework.http.HttpStatus;

public interface BaseError {

    HttpStatus getHttpStatus();

    String getCode();

    String getMsg();
}
