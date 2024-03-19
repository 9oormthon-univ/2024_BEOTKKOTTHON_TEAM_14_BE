package com.example.demo.controller;

import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.request.LoginRequestDto;
import com.example.demo.controller.dto.response.user.LoginResponseDto;
import com.example.demo.exception.SuccessCode;
import com.example.demo.service.login.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        loginService.signIn(loginRequestDto);
        return BaseResponse.success(SuccessCode.LOGIN_SUCCESS,SuccessCode.LOGIN_SUCCESS.getMessage());
    }

    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody @Valid LoginRequestDto loginRequestDto) {

        loginService.register(loginRequestDto);
        return BaseResponse.success(SuccessCode.SIGNIN_SUCCESS,SuccessCode.SIGNIN_SUCCESS.getMessage());
    }

    @PostMapping("/logout")
    public BaseResponse<String> logout() {
        loginService.logout();
        return BaseResponse.success(SuccessCode.SIGNOUT_SUCCESS,SuccessCode.SIGNOUT_SUCCESS.getMessage());
    }

}
