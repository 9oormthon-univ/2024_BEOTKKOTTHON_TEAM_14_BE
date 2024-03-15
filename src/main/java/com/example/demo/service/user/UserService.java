package com.example.demo.service.user;

import com.example.demo.controller.dto.response.user.UserResponseDto;
import com.example.demo.domain.user.User;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.NotFoundException;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto getUserBySocialEmail(String socialEmail){
        User user = userRepository.findBySocialEmail(socialEmail).orElseThrow(()->new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION,ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
        return UserResponseDto.of(socialEmail);
    }

}
