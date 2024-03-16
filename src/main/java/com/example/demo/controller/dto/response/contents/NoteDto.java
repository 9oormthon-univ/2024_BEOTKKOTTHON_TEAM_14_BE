package com.example.demo.controller.dto.response.contents;

import com.example.demo.domain.contents.Note;
import lombok.*;

@Getter
@Setter
public class NoteDto {
    private String name;
    private String content;
    private String password;

    // Entity를 DTO로 변환하는 메서드
    public static NoteDto fromEntity(Note note) {
        NoteDto dto = new NoteDto();
        dto.setName(note.getName());
        dto.setContent(note.getContent());
        dto.setPassword(note.getPassword());
        return dto;
    }
}
