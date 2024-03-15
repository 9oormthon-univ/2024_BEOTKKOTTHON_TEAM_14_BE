package com.example.demo.controller.dto.response.user;


import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {
    private String socialEmail;
    private String userName;

    public static UserResponseDto of(String socialEmail) { //entity -> dto 변환
        return UserResponseDto
                .builder()
                .socialEmail(socialEmail)
                .build();
    }

}
