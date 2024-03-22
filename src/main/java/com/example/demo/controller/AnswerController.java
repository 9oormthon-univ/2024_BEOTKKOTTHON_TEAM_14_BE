package com.example.demo.controller;

import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.request.AnswerRequestDto;
import com.example.demo.domain.user.User;
import com.example.demo.domain.will.Answer;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.SuccessCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.service.will.AnswerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/create")
    public BaseResponse<String> createAnswer(@RequestBody @Valid AnswerRequestDto answerRequestDto, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new CustomException(ErrorCode.INVALID_USER_EXCEPTION, ErrorCode.INVALID_USER_EXCEPTION.getMessage());
        }

        answerService.createAnswer(answerRequestDto, session);
        return BaseResponse.success(SuccessCode.CREATE_COMPLETE_SUCCESS, SuccessCode.CREATE_COMPLETE_SUCCESS.getMessage());
    }

    @DeleteMapping("/delete")
    public BaseResponse<String> deleteAnswer(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new CustomException(ErrorCode.INVALID_USER_EXCEPTION, ErrorCode.INVALID_USER_EXCEPTION.getMessage());
        }

        answerService.deleteAnswer(user.getId());
        return BaseResponse.success(SuccessCode.DELETE_SUCCESS, SuccessCode.DELETE_SUCCESS.getMessage());
    }


    @GetMapping("/get")
    public BaseResponse<Answer> getAnswer(HttpSession session) {
        Answer answer = answerService.getAnswer(session);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, answer);
    }

}
