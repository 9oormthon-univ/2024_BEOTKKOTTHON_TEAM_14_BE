package com.example.demo.controller;


import com.example.demo.controller.dto.response.contents.NoteDto;
import com.example.demo.domain.contents.Note;
import com.example.demo.service.contents.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public Note createNote(@RequestBody NoteDto request) {
        return noteService.createNote(request.getName(), request.getContent(), request.getPassword());
    }

    @GetMapping
    public List<Note> getNotes() {
        return noteService.getAllNotes();
    }

//    @PutMapping("/{name}")
//    public ResponseEntity<Note> updateNote(@PathVariable String name, @RequestParam String password, @RequestBody NoteDto request) {
//        Note updatedNote = noteService.updateNote(name, password, request);
//        return ResponseEntity.ok(updatedNote);
//    }

}