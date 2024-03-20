package com.example.demo.controller;

import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.request.MessageRequestDto;
import com.example.demo.domain.user.User;
import com.example.demo.exception.SuccessCode;
import com.example.demo.service.Message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<BaseResponse<String>> createMessage(@RequestBody MessageRequestDto requestDto, @AuthenticationPrincipal User user) {
        messageService.createMessage(requestDto, user);
        BaseResponse<String> response = BaseResponse.success(SuccessCode.CREATE_COMPLETE_SUCCESS, SuccessCode.CREATE_COMPLETE_SUCCESS.getMessage());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<String>> updateMessage(@PathVariable Long id, @RequestBody MessageRequestDto requestDto, @AuthenticationPrincipal User user) {
        messageService.updateMessage(id, requestDto, user);
        BaseResponse<String> response = BaseResponse.success(SuccessCode.UPDATE_COMPLETE_SUCCESS, SuccessCode.UPDATE_COMPLETE_SUCCESS.getMessage());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<String>> deleteMessage(@PathVariable Long id, @AuthenticationPrincipal User user) {
        messageService.deleteMessage(id, user);
        BaseResponse<String> response = BaseResponse.success(SuccessCode.DELETE_SUCCESS, SuccessCode.DELETE_SUCCESS.getMessage());
        return ResponseEntity.ok(response);
    }
}




