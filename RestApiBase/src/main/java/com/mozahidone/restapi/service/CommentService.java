package com.mozahidone.restapi.service;

import com.mozahidone.restapi.model.Comment;
import com.mozahidone.restapi.model.Post;
import com.mozahidone.restapi.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	
	public List<Comment> findAllByPost(Post post){
		return commentRepository.findAllByPost(post);
	}

}