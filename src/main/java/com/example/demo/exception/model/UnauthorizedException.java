package com.example.demo.exception.model;


import com.example.demo.exception.ErrorCode;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}