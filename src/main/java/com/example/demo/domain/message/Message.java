package com.example.demo.domain.message;

import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(length = 30)
    private String message;

    @Column(name = "receiver")
    private String receiver;
}
