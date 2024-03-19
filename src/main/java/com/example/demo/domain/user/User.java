package com.example.demo.domain.user;


import com.example.demo.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Column(unique = true)
    String email;

    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    @Column(unique = true)
    String phoneNumber;

    @Column
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    String name;

    @Column
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    String password;
}
