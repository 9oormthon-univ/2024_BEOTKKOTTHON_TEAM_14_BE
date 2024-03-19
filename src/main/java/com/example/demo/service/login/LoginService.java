package com.example.demo.service.login;

import com.example.demo.controller.dto.request.LoginRequestDto;
import com.example.demo.domain.user.User;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final LoginRepository loginRepository;
    public void signIn(LoginRequestDto loginRequestDto) {
        User user = loginRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new CustomException(ErrorCode.INVALID_PASSWORD_EXCEPTION, ErrorCode.INVALID_PASSWORD_EXCEPTION.getMessage());
        }
        System.out.println("로그인 성공");
    }

    public void register(LoginRequestDto loginRequestDto){
        Optional<User> user = loginRepository.findByEmail(loginRequestDto.getEmail());
        String encodedPassword = passwordEncoder.encode(loginRequestDto.getPassword());
        if(user.isEmpty()){
            User newUser = User.builder()
                    .email(loginRequestDto.getEmail())
                    .password(encodedPassword)
                    .build();
            loginRepository.save(newUser);
            System.out.println("회원가입성공");
        }else {
            System.out.println("이미 존재하는 유저");
            throw new CustomException(ErrorCode.DUPLICATE_USER_EXCEPTION,ErrorCode.DUPLICATE_USER_EXCEPTION.getMessage());
        }

    }
    public void logout(){
        SecurityContextHolder.clearContext();
        System.out.println("로그아웃 성공");
    }
}