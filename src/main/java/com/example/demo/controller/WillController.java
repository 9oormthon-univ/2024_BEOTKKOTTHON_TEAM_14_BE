package com.example.demo.controller;


import com.example.demo.controller.dto.response.will.WillResponseDto;
import com.example.demo.domain.will.Will;
import com.example.demo.service.will.WillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class WillController {
    private final WillService willService;

    @Autowired
    public WillController(WillService willService) {
        this.willService = willService;
    }

    @PostMapping
    public Will createNote(@RequestBody WillResponseDto request) {
        return willService.createNote(request.getName(), request.getContent(), request.getPassword());
    }

    @GetMapping
    public List<Will> getNotes() {
        return willService.getAllNotes();
    }

//    @PutMapping("/{name}")
//    public ResponseEntity<Note> updateNote(@PathVariable String name, @RequestParam String password, @RequestBody NoteDto request) {
//        Note updatedNote = noteService.updateNote(name, password, request);
//        return ResponseEntity.ok(updatedNote);
//    }

}