package com.example.demo.domain.user;


import com.example.demo.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    String email; //확장성을 위해 넣어둠

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    String password;
}
