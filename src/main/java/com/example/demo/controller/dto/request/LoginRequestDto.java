package com.example.demo.controller.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequestDto {

    String email;

    String phoneNumber;

    String name;

    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[A-Za-z0-9]).{5,}$", message = "비밀번호는 최소 5자 이상이고, 특수 문자 하나를 포함해야 합니다.")
    private String password;
}