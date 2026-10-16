package com.botelho.loester.api_blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.botelho.loester.api_blog.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
