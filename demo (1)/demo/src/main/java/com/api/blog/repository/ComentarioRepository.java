package com.api.blog.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.blog.model.ComentarioModel;

public interface ComentarioRepository extends JpaRepository<ComentarioModel, UUID> {

}
