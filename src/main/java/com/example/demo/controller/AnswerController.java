package com.example.demo.controller;

import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.request.AnswerRequestDto;
import com.example.demo.exception.SuccessCode;
import com.example.demo.service.will.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/create")
    public BaseResponse<String> createAnswer(@RequestBody @Valid AnswerRequestDto answerRequestDto) {
        answerService.createAnswer(answerRequestDto);
        return BaseResponse.success(SuccessCode.CREATE_COMPLETE_SUCCESS, SuccessCode.CREATE_COMPLETE_SUCCESS.getMessage());
    }

    @DeleteMapping("/delete/{userId}")
    public BaseResponse<String> deleteAnswer(@PathVariable Long userId) {
        answerService.deleteAnswer(userId);
        return BaseResponse.success(SuccessCode.DELETE_SUCCESS, SuccessCode.DELETE_SUCCESS.getMessage());
    }

    @GetMapping("/get/{userId}")
    public BaseResponse<String> getAnswer(@PathVariable Long userId) {
        answerService.getAnswer(userId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, SuccessCode.GET_SUCCESS.getMessage());
    }
}
