package com.example.demo.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerRequestDto {
    private String picture;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;
}
