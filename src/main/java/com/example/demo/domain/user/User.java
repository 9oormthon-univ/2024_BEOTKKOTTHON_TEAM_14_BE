package com.example.demo.domain.user;


import com.example.demo.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
public class User extends BaseEntity {
    @Column(unique = true)
    String email; //확장성을 위해 넣어둠

    @Column(unique = true)
    String nickname;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 최소 8자 이상이어야 하며, 최소 하나의 대문자, 소문자, 숫자, 특수 문자를 포함해야 합니다.")
    String password;
}
