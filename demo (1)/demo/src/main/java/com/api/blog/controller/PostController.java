package com.api.blog.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.PostResponseDto;
import com.api.blog.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/api")
public class PostController {

	private final PostService postService;
	public PostController (PostService postService) {
		
		this.postService = postService;
	}
	
	// LISTAR TODOS OS POSTS
	@GetMapping("/posts")
	public ResponseEntity<List<PostResponseDto>> getAllPosts() {
	    return ResponseEntity.ok(postService.findAll());
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostResponseDto> getPostById(@PathVariable UUID id) {
	    return ResponseEntity.ok(postService.findById(id));
	}

	@PostMapping("/newpost")
	public ResponseEntity<PostResponseDto> createPost(
	        @RequestBody @Valid PostRequestDto dto) {
	    PostResponseDto created = postService.createPost(dto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	}
	

