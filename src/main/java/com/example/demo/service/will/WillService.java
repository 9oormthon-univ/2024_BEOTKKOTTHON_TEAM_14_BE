package com.example.demo.service.will;

import com.example.demo.controller.dto.request.WillRequestDto;
import com.example.demo.domain.user.User;
import com.example.demo.domain.will.Will;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.WillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WillService {
    private final WillRepository willRepository;
    private final LoginRepository loginRepository;

    public void createWill(WillRequestDto willRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //현재 인증된 사용자 정보
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CustomException(ErrorCode.INVALID_USER_EXCEPTION, ErrorCode.INVALID_USER_EXCEPTION.getMessage());
        }
        Long currentId = willRequestDto.getUserId();
        Optional<Will> existWill = willRepository.findByUserId(currentId); // 유서가 존재하는지 확인
        if (existWill.isPresent()) { // 유서를 이미 작성한 경우 예외 발생
            throw new CustomException(ErrorCode.DUPLICATE_WILL_EXCEPTION, ErrorCode.DUPLICATE_WILL_EXCEPTION.getMessage());
        } else { // 작성된 유서가 없는 경우 새로 작성
            Optional<User> userOptional = loginRepository.findById(currentId);
            if (userOptional.isEmpty()) { // 유저가 존재하지 않는 경우 예외 발생
                throw new CustomException(ErrorCode.DUPLICATE_WILL_EXCEPTION, ErrorCode.DUPLICATE_WILL_EXCEPTION.getMessage());
            }
            String currentUsername = authentication.getName(); // 현재 사용자의 이름을 가져옴
            // UserDetailsService에서 loadUserByUsername 메서드를 구현하여 UserDetails 객체를 반환하는 경우 UserDetails 객체를 형변환하여 사용자 정보를 얻을 수 있음
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            //Long currentUserId = getUserIdFromDatabaseByUsername(currentUsername); // 사용자 이름으로부터 ID를 데이터베이스에서 조회하는 메서드 호출
            // 또는 UserDetails 객체에서 직접 ID를 얻을 수 있으면 다음과 같이 사용 가능
            // Long currentUserId = ((CustomUserDetails) userDetails).getId();

            User user = userOptional.get(); // 유저가 존재하는 경우 유저 정보 가져오기
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