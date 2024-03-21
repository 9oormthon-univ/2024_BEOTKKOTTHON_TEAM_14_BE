package com.example.demo.repository;

import com.example.demo.domain.message.Message;
import com.example.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findByIdAndUser(Long id, User user);

    List<Message> findByUser(User user);
}
