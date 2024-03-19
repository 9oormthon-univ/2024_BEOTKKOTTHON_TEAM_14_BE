package com.example.demo.domain.will;

import com.example.demo.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Will extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    public Will(String name, String content, String password) {
        this.name = name;
        this.content = content;
        this.password = password;
    }

}

