package com.example.demo.controller;

import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.response.UserResponseDto;
import com.example.demo.exception.SuccessCode;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public BaseResponse<UserResponseDto> getUserByUserId(@RequestParam Long userId){
        final UserResponseDto userResponseDto = userService.getUser(userId);
        return BaseResponse.success(SuccessCode.GET_USER_INFO_SUCCESS, userResponseDto);
    }
}
