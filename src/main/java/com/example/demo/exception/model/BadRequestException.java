package com.example.demo.exception.model;

import com.example.demo.exception.ErrorCode;

public class BadRequestException extends CustomException{
    public BadRequestException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
