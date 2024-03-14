package com.example.demo.controller;

import com.example.demo.domain.letter.Letter;
import com.example.demo.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/letters")
public class LetterController {

    @Autowired
    private LetterService letterService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> createLetter(@PathVariable("userId") Long userId, @RequestBody String content) {
        Letter letter = letterService.createLetter(userId, content);
        return new ResponseEntity<>(letter, HttpStatus.CREATED);
    }

    @PutMapping("/{letterId}")
    public ResponseEntity<?> updateLetter(@PathVariable("letterId") Long letterId, @RequestBody String content) {
        Letter updatedLetter = letterService.updateLetter(letterId, content);
        return new ResponseEntity<>(updatedLetter, HttpStatus.OK);
    }
}
