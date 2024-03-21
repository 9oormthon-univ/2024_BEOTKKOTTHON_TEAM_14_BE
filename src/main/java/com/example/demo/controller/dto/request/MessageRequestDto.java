package com.example.demo.controller.dto.request;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class MessageRequestDto {
    private String message;
    private String receiver;
}
