package com.example.demo.repository;

import com.example.demo.domain.contents.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findBynameAndPassword(String name, String password);

}