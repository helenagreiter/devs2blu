package com.api.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.PostResponseDto;
import com.api.blog.mapper.PostMapper;
import com.api.blog.model.PostModel;
import com.api.blog.repository.PostRepository;

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll(){
    	List<PostModel> posts = postRepository.findAll();
    	List<PostResponseDto> dtos = new ArrayList<>();
       
    	for (PostModel post : posts) {
    		dtos.add(postMapper.toDto(post));
    	}
    	return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto findById(UUID id) {

        Optional<PostModel> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            throw new RuntimeException("Post não encontrado com ID " + id);
        }

        PostModel post = optionalPost.get();

        return postMapper.toDto(post);
    }

	@Override
	@Transactional
	public PostResponseDto createPost(PostRequestDto dto) {
		PostModel post = postMapper.toEntity(dto);
		PostModel saved = postRepository.save(post);
		return postMapper.toDto(saved);
	}

}