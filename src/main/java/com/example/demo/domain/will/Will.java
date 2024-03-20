package com.example.demo.domain.will;

import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Will extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String signature; //서명

    @Column(length = 500)
    private String answerFree = null;
}