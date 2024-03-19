package com.example.demo.service.will;

import com.example.demo.controller.dto.request.WillRequestDto;
import com.example.demo.domain.will.Will;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.repository.WillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WillService {
    private final WillRepository willRepository;

    public void createWill(WillRequestDto willRequestDto) {
        Optional<Will> existWill = willRepository.findByUserId(String.valueOf(willRequestDto.getUserId())); //유서가 존재하는지 확인
        if (existWill.isPresent()) { //유서를 이미 작성한 경우 예외 발생
            throw new CustomException(ErrorCode.DUPLICATE_WILL_EXCEPTION, ErrorCode.DUPLICATE_WILL_EXCEPTION.getMessage());
        } else { //작성된 유서가 없는 경우 새로 작성
            Will will = Will.builder()
                    .answerFree(willRequestDto.getAnswerFree())
                    .signature(willRequestDto.getSignature())
                    .build();
            System.out.println("저장 성공");
            willRepository.save(will);
        }
    }

    public void deleteWill(Long userId) {
        Will will = willRepository.findByUserId(String.valueOf(userId)).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_WILL, ErrorCode.NOT_FOUND_WILL.getMessage()));
        System.out.println("삭제 성공");
        willRepository.delete(will);
    }

}