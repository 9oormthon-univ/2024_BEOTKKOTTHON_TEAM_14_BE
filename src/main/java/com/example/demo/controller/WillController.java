package com.example.demo.controller;


import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.request.WillRequestDto;
import com.example.demo.domain.user.User;
import com.example.demo.domain.will.Will;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.SuccessCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.service.will.WillService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/will")
public class WillController {
    private final WillService willService;

    @PostMapping("/create")
    public BaseResponse<String> createWill(@RequestBody @Valid WillRequestDto willRequestDto, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new CustomException(ErrorCode.INVALID_USER_EXCEPTION, ErrorCode.INVALID_USER_EXCEPTION.getMessage());
        }
        willService.createWill(willRequestDto, session);
        return BaseResponse.success(SuccessCode.CREATE_COMPLETE_SUCCESS,SuccessCode.CREATE_COMPLETE_SUCCESS.getMessage());
    }

    @GetMapping("/get")
    public BaseResponse<Will> getAnswer(HttpSession session) {
        Will will = willService.getWill(session);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, will);
    }

    @DeleteMapping("/delete")
    public BaseResponse<String> deleteWill(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new CustomException(ErrorCode.INVALID_USER_EXCEPTION, ErrorCode.INVALID_USER_EXCEPTION.getMessage());
        }

        willService.deleteWill(user.getId());
        return BaseResponse.success(SuccessCode.DELETE_SUCCESS, SuccessCode.DELETE_SUCCESS.getMessage());
    }
}