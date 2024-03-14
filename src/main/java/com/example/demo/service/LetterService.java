package com.example.demo.service;

import com.example.demo.domain.letter.Letter;
import com.example.demo.domain.user.User;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.model.CustomException;
import com.example.demo.repository.LetterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LetterService {

    @Autowired
    private LetterRepository letterRepository;

    // UserRepository만들어서 다시 로직 구현 할것! <- 객체 정보를 받아와야하니깐
    @PersistenceContext
    private EntityManager entityManager;

    private User findUserById(Long userId) {
        return entityManager.find(User.class, userId);
    }

    public Letter createLetter(Long userId, String content) {
        User user = findUserById(userId);
        Letter letter = new Letter(user, content);
        return letterRepository.save(letter);
    }

    public Letter updateLetter(Long letterId, String content) {
        Letter letter = letterRepository.findById(letterId)
                .orElseThrow(() -> new CustomException(ErrorCode.LETTER_NOT_FOUND, "해당 id에 해당하는 유서를 찾을 수 없습니다: " + letterId));

        letter.setContent(content);
        return letterRepository.save(letter);
    }
}


