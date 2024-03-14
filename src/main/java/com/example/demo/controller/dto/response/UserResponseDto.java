package com.example.demo.controller.dto.response;

import com.example.demo.domain.user.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {
    private Long userId;
    private String userName;

    public static UserResponseDto of(Long userId, String userName) { //entity -> dto 변환
        return UserResponseDto
                .builder()
                .userId(userId)
                .userName(userName)
                .build();
    }

}
