package com.example.demo.controller.dto.response.user;

import com.example.demo.exception.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private String message;
    public static LoginResponseDto of(SuccessCode successCode){
        return new LoginResponseDto(successCode.getMessage());
    }

}