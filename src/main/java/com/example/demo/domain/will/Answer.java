package com.example.demo.domain.will;

import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
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
    @Builder.Default
    @Lob
    private String picture = "";

    @Column(length = 100)
    @Builder.Default
    private String answer1 = "";

    @Column(length = 100)
    @Builder.Default
    private String answer2 = "";

    @Column(length = 100)
    @Builder.Default
    private String answer3 = "";

    @Column(length = 100)
    @Builder.Default
    private String answer4 = "";

    @Column(length = 100)
    @Builder.Default
    private String answer5 = "";
}
