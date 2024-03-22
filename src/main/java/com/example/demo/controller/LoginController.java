package com.example.demo.controller;

import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.request.LoginRequestDto;
import com.example.demo.domain.user.User;
import com.example.demo.exception.SuccessCode;
import com.example.demo.service.login.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpSession session, HttpServletResponse response) {
        loginService.signIn(loginRequestDto, session, response);

        // 확인용코드
        User user = (User) session.getAttribute("user");
        System.out.println("세션에 저장된 사용자 정보: " + user);

        return BaseResponse.success(SuccessCode.LOGIN_SUCCESS,SuccessCode.LOGIN_SUCCESS.getMessage());
    }

    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody @Valid LoginRequestDto loginRequestDto) {

        loginService.register(loginRequestDto);
        return BaseResponse.success(SuccessCode.SIGNIN_SUCCESS,SuccessCode.SIGNIN_SUCCESS.getMessage());
    }

    @PostMapping("/logout")
    public BaseResponse<String> logout(HttpSession session) {
        loginService.logout(session);
        return BaseResponse.success(SuccessCode.SIGNOUT_SUCCESS,SuccessCode.SIGNOUT_SUCCESS.getMessage());
    }

    @DeleteMapping("/delete/{phoneNumber}")
    public BaseResponse<String> delete(@PathVariable String phoneNumber){
        loginService.delete(phoneNumber);
        return BaseResponse.success(SuccessCode.DELETE_SUCCESS, SuccessCode.DELETE_SUCCESS.getMessage());
    }
}
