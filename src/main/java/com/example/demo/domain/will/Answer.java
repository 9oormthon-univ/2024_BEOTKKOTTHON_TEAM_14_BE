package com.example.demo.domain.will;

import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
public class Answer extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String answer1 = null;

    @Column
    private String answer2 = null;

    @Column
    private String answer3 = null;

    @Column
    private String answer4 = null;

    @Column
    private String answer5 = null;
}
