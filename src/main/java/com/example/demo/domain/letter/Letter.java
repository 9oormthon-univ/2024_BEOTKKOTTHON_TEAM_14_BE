package com.example.demo.domain.letter;

import com.example.demo.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "letter")
public class Letter extends BaseEntity {

    // 유저당 하나의 유서 - 일대일 관계
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "letter_content", length = 300)
    private String content;

    public Letter(User user, String content) {
        this.user = user;
        this.content = content;
    }
}

