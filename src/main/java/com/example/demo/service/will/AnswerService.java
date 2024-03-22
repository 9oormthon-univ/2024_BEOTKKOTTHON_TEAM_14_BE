package com.example.demo.service.will;

import com.example.demo.controller.dto.request.AnswerRequestDto;
import com.example.demo.domain.user.User;
import com.example.demo.domain.will.Answer;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.repository.AnswerRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void createAnswer(AnswerRequestDto answerRequestDto, HttpSession session) {
        // 세션에서 로그인된 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new CustomException(ErrorCode.INVALID_USER_EXCEPTION, ErrorCode.INVALID_USER_EXCEPTION.getMessage());
        }

        Long currentId = user.getId();
        Optional<Answer> existAnswer = answerRepository.findByUserId(currentId);
        if (existAnswer.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_ANSWER_EXCEPTION, ErrorCode.DUPLICATE_ANSWER_EXCEPTION.getMessage());
        } else {
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
        Answer answer = answerRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ANSWER, ErrorCode.NOT_FOUND_ANSWER.getMessage()));

        System.out.println("삭제 성공");
        answerRepository.delete(answer);
    }

    public Answer getAnswer(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new CustomException(ErrorCode.INVALID_USER_EXCEPTION, ErrorCode.INVALID_USER_EXCEPTION.getMessage());
        }
        return answerRepository.findByUserId(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ANSWER, ErrorCode.NOT_FOUND_ANSWER.getMessage()));
    }
}
