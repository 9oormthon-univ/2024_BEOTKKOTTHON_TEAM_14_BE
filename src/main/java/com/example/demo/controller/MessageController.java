package com.example.demo.controller;

import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.request.MessageRequestDto;
import com.example.demo.domain.message.Message;
import com.example.demo.domain.user.User;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.SuccessCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.service.Message.MessageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<String>> createMessage(@RequestBody MessageRequestDto requestDto, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new CustomException(ErrorCode.INVALID_USER_EXCEPTION, ErrorCode.INVALID_USER_EXCEPTION.getMessage());
        }

        messageService.createMessage(requestDto, user);
        BaseResponse<String> response = BaseResponse.success(SuccessCode.CREATE_COMPLETE_SUCCESS, SuccessCode.CREATE_COMPLETE_SUCCESS.getMessage());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<Message>>> getAllMessages(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new CustomException(ErrorCode.INVALID_USER_EXCEPTION, ErrorCode.INVALID_USER_EXCEPTION.getMessage());
        }

        List<Message> messages = messageService.getAllMessagesByUser(user);
        BaseResponse<List<Message>> response = BaseResponse.success(SuccessCode.GET_MESSAGE_SUCCESS, messages);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<String>> updateMessage(@PathVariable Long id, @RequestBody MessageRequestDto requestDto, HttpSession session) {
        User user = (User) session.getAttribute("user");
        messageService.updateMessage(id, requestDto, user);
        BaseResponse<String> response = BaseResponse.success(SuccessCode.UPDATE_COMPLETE_SUCCESS, SuccessCode.UPDATE_COMPLETE_SUCCESS.getMessage());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<String>> deleteMessage(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        messageService.deleteMessage(id, user);
        BaseResponse<String> response = BaseResponse.success(SuccessCode.DELETE_SUCCESS, SuccessCode.DELETE_SUCCESS.getMessage());
        return ResponseEntity.ok(response);
    }
}




