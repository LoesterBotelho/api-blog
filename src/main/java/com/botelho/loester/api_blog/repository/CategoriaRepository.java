package com.botelho.loester.api_blog.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.botelho.loester.api_blog.model.CategoriaModel;

public interface CategoriaRepository
        extends JpaRepository<CategoriaModel, UUID> {
}