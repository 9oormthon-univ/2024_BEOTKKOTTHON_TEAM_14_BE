package com.example.demo.controller;

import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.request.LoginRequestDto;
import com.example.demo.controller.dto.response.user.LoginResponseDto;
import com.example.demo.exception.SuccessCode;
import com.example.demo.service.login.LoginService;
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
    public BaseResponse<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        loginService.signIn(loginRequestDto);
        return BaseResponse.success(SuccessCode.LOGIN_SUCCESS, new LoginResponseDto("로그인 성공"));
    }

    @PostMapping("/register")
    public BaseResponse<LoginResponseDto> register(@RequestBody LoginRequestDto loginRequestDto) {
        loginService.register(loginRequestDto);
        return BaseResponse.success(SuccessCode.SIGNIN_SUCCESS, new LoginResponseDto("회원가입 성공"));
    }

    @PostMapping("/logout")
    public BaseResponse<LoginResponseDto> logout() {
        loginService.logout();
        return BaseResponse.success(SuccessCode.SIGNOUT_SUCCESS, new LoginResponseDto("로그아웃 성공"));
    }

}
