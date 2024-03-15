package com.example.demo.repository;

import com.example.demo.domain.user.SocialType;
import com.example.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsBySocialEmailAndSocialType(String socialEmail, SocialType socialType); //카카오 계정이 존재하는지 여부 판단

    Optional<User> findBySocialEmail(String socialEmail);

    Optional<User> findByRefreshToken(String refreshToken);
}
