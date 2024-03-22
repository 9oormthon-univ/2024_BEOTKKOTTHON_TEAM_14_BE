package com.example.demo.service.login;

import com.example.demo.controller.dto.request.LoginRequestDto;
import com.example.demo.domain.user.User;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.repository.LoginRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final LoginRepository loginRepository;

    public void signIn(LoginRequestDto loginRequestDto, HttpSession session, HttpServletResponse response) {
        User user = loginRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD_EXCEPTION, ErrorCode.INVALID_PASSWORD_EXCEPTION.getMessage());
        }

        // 로그인 성공 시 세션에 사용자정보 저장
        session.setAttribute("user", user);

        // 쿠키 설정
        Cookie cookie = new Cookie("sessionId", session.getId());
        cookie.setPath("/");
        response.addCookie(cookie);

        // 쿠키에 저장된 정보 확인
        System.out.println("쿠키 이름: " + cookie.getName());
        System.out.println("쿠키 값: " + cookie.getValue());

        System.out.println("로그인 성공");
    }

    public void register(LoginRequestDto loginRequestDto) {
        Optional<User> user = loginRepository.findByEmail(loginRequestDto.getEmail());
        String encodedPassword = passwordEncoder.encode(loginRequestDto.getPassword());
        if (user.isEmpty()) {
            User newUser = User.builder()
                    .email(loginRequestDto.getEmail())
                    .phoneNumber(loginRequestDto.getPhoneNumber())
                    .name(loginRequestDto.getName())
                    .password(encodedPassword)
                    .build();
            loginRepository.save(newUser);
            System.out.println("회원가입성공");
        } else {
            System.out.println("이미 존재하는 유저");
            throw new CustomException(ErrorCode.DUPLICATE_USER_EXCEPTION, ErrorCode.DUPLICATE_USER_EXCEPTION.getMessage());
        }

    }

    public void logout(HttpSession session) {
        session.invalidate(); // 세션 무효화해주기!
        SecurityContextHolder.clearContext();
        System.out.println("로그아웃 성공");
    }

    public void delete(String phoneNumber) {
        Optional<User> user = loginRepository.findByEmail(phoneNumber);
        if (user.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage());
        } else {
            User user1 = user.get();
            if (!user1.getPhoneNumber().equals(phoneNumber)) {
                throw new CustomException(ErrorCode.INVALID_PHONE_NUMBER_EXCEPTION, ErrorCode.INVALID_PHONE_NUMBER_EXCEPTION.getMessage());
            } else {
                System.out.println("회원 탈퇴 성공");
                loginRepository.delete(user1);
            }
        }
    }
}