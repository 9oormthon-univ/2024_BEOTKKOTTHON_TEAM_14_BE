package com.example.demo.service.contents;

import com.example.demo.controller.dto.response.contents.NoteDto;
import com.example.demo.domain.contents.Note;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.NotFoundException;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(String name, String content, String password) {
        Note note = new Note();
        note.setName(name);
        note.setContent(content);
        note.setPassword(password);
        return noteRepository.save(note);
    }

    public Note updateNote(String name, String password, NoteDto request) {
        // 사용자 이름과 비밀번호를 사용하여 해당 사용자의 유서를 확인
        Note existingNote = noteRepository.findBynameAndPassword(name, password)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, "Note not found for user: " + name));

        // 유서의 내용을 업데이트
        existingNote.setName(request.getName());
        existingNote.setContent(request.getContent());
        existingNote.setPassword(request.getPassword());

        // 변경된 유서를 저장하고 반환
        return noteRepository.save(existingNote);
    }


    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
}