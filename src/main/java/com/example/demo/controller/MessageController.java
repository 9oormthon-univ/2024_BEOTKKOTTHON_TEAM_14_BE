package com.example.demo.controller;

import com.example.demo.controller.dto.request.MessageRequestDto;
import com.example.demo.domain.user.User;
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
    public ResponseEntity<Void> createMessage(@RequestBody MessageRequestDto requestDto, @AuthenticationPrincipal User user) {
        messageService.createMessage(requestDto, user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateMessage(@PathVariable Long id, @RequestBody MessageRequestDto requestDto, @AuthenticationPrincipal User user) {
        messageService.updateMessage(id, requestDto, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id, @AuthenticationPrincipal User user) {
        messageService.deleteMessage(id, user);
        return ResponseEntity.ok().build();
    }
}



