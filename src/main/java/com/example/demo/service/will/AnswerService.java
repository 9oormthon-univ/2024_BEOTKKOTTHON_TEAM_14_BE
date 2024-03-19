package com.example.demo.service.will;

import com.example.demo.controller.dto.request.AnswerRequestDto;
import com.example.demo.domain.will.Answer;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void createAnswer(AnswerRequestDto answerRequestDto) {
        Optional<Answer> existAnswer = answerRepository.findByUserId(String.valueOf(answerRequestDto.getUserId()));
        if (existAnswer.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_ANSWER_EXCEPTION, ErrorCode.DUPLICATE_ANSWER_EXCEPTION.getMessage());
        } else {
            Answer answer = Answer.builder()
                    .answer1(answerRequestDto.getAnswer1())
                    .answer2(answerRequestDto.getAnswer2())
                    .answer3(answerRequestDto.getAnswer3())
                    .answer4(answerRequestDto.getAnswer4())
                    .answer5(answerRequestDto.getAnswer5())
                    .build();
            answerRepository.save(answer);
        }
    }
}
