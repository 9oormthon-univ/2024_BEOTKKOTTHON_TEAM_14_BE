package com.example.demo.controller;

import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.request.LoginRequestDto;
import com.example.demo.controller.dto.response.user.LoginResponseDto;
import com.example.demo.domain.user.User;
import com.example.demo.exception.SuccessCode;
import com.example.demo.service.login.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.representer.BaseRepresenter;

import java.util.Optional;

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

    @DeleteMapping("/delete/{phoneNumber}")
    public BaseResponse<String> delete(@PathVariable String phoneNumber){
        loginService.delete(phoneNumber);
        return BaseResponse.success(SuccessCode.DELETE_SUCCESS, SuccessCode.DELETE_SUCCESS.getMessage());
    }
}
