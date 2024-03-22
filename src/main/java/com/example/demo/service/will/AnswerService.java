package com.example.demo.service.will;

import com.example.demo.controller.dto.request.AnswerRequestDto;
import com.example.demo.domain.user.User;
import com.example.demo.domain.will.Answer;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final LoginRepository loginRepository;

    public void createAnswer(AnswerRequestDto answerRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //현재 인증된 사용자 정보
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CustomException(ErrorCode.INVALID_USER_EXCEPTION, ErrorCode.INVALID_USER_EXCEPTION.getMessage());
        }
        Long currentId = answerRequestDto.getUserId();
        Optional<Answer> existAnswer = answerRepository.findByUserId(currentId);
        if (existAnswer.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_ANSWER_EXCEPTION, ErrorCode.DUPLICATE_ANSWER_EXCEPTION.getMessage());
        } else {
            Optional<User> userOptional = loginRepository.findById(currentId);
            if (userOptional.isEmpty()) {
                throw new CustomException(ErrorCode.DUPLICATE_WILL_EXCEPTION, ErrorCode.DUPLICATE_WILL_EXCEPTION.getMessage());
            }
            User user = userOptional.get();
            Answer answer = Answer.builder()
                    .user(user)
                    .picture(answerRequestDto.getPicture())
                    .answer1(answerRequestDto.getAnswer1())
                    .answer2(answerRequestDto.getAnswer2())
                    .answer3(answerRequestDto.getAnswer3())
                    .answer4(answerRequestDto.getAnswer4())
                    .answer5(answerRequestDto.getAnswer5())
                    .build();
            System.out.println("유서 질문 답변 저장 성공");
            answerRepository.save(answer);
        }
    }

    public void deleteAnswer(Long userId) {
        Answer answer = answerRepository.findByUserId(userId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ANSWER, ErrorCode.NOT_FOUND_ANSWER.getMessage()));
        System.out.println("삭제 성공");
        answerRepository.delete(answer);
    }

    public Optional<Answer> getAnswer(Long userId) {
        Optional<Answer> answer = answerRepository.findByUserId(userId);
        return answer;
    }
}
