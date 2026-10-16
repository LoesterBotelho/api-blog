package com.botelho.loester.api_blog.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.botelho.loester.api_blog.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
