package com.example.demo.service.will;

import com.example.demo.controller.dto.request.WillRequestDto;
import com.example.demo.domain.user.User;
import com.example.demo.domain.will.Will;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.WillRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WillService {
    private final WillRepository willRepository;
    private final LoginRepository loginRepository;

    public void createWill(WillRequestDto willRequestDto, HttpSession session) {
        // 세션에서 로그인된 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new CustomException(ErrorCode.INVALID_USER_EXCEPTION, ErrorCode.INVALID_USER_EXCEPTION.getMessage());
        }

        Long currentId = user.getId();
        Optional<Will> existWill = willRepository.findByUserId(currentId); // 유서가 존재하는지 확인
        if (existWill.isPresent()) { // 유서를 이미 작성한 경우 예외 발생
            throw new CustomException(ErrorCode.DUPLICATE_WILL_EXCEPTION, ErrorCode.DUPLICATE_WILL_EXCEPTION.getMessage());
        } else { // 작성된 유서가 없는 경우 새로 작성
            Optional<User> userOptional = loginRepository.findById(currentId);
            if (userOptional.isEmpty()) { // 유저가 존재하지 않는 경우 예외 발생
                throw new CustomException(ErrorCode.DUPLICATE_WILL_EXCEPTION, ErrorCode.DUPLICATE_WILL_EXCEPTION.getMessage());
            }
            Will will = Will.builder()
                    .user(user)
                    .answerFree(willRequestDto.getAnswerFree())
                    .signature(willRequestDto.getSignature())
                    .build();
            System.out.println("저장 성공");
            willRepository.save(will);
        }
    }

    public void deleteWill(Long userId) {
        Will will = willRepository.findByUserId(userId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_WILL, ErrorCode.NOT_FOUND_WILL.getMessage()));
        System.out.println("삭제 성공");
        willRepository.delete(will);
    }

}