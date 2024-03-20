package com.example.demo.repository;

import com.example.demo.domain.message.Message;
import com.example.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findByIdAndUser(Long id, User user);
}
