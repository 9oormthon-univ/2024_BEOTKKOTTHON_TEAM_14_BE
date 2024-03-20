package com.example.demo.service.Message;

import com.example.demo.controller.dto.request.MessageRequestDto;
import com.example.demo.domain.message.Message;
import com.example.demo.domain.user.User;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void createMessage(MessageRequestDto requestDto, User user) {
        Message message = new Message();
        message.setMessage(requestDto.getMessage());
        message.setReceiver(requestDto.getReceiver());
        message.setUser(user);
        System.out.println("메세지 작성 성공");
        messageRepository.save(message);
    }

    public Message updateMessage(Long messageId, MessageRequestDto requestDto, User user) {
        Message message = messageRepository.findByIdAndUser(messageId, user);
        if (message != null) {
            message.setMessage(requestDto.getMessage());
            message.setReceiver(requestDto.getReceiver());
            System.out.println("메세지 수정 성공");
            return messageRepository.save(message);
        } else {
            throw new CustomException(ErrorCode.NOT_FOUND_MESSAGE, ErrorCode.NOT_FOUND_MESSAGE.getMessage());
        }
    }

    public void deleteMessage(Long messageId, User user) {
        Message message = messageRepository.findByIdAndUser(messageId, user);
        if (message != null) {
            System.out.println("메세지 삭제 성공");
            messageRepository.delete(message);
        }
    }
}


