package com.example.demo.controller.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageRequestDto {
    private String message;
    private String receiver;
}
