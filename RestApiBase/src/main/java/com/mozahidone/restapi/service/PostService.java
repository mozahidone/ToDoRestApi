package com.mozahidone.restapi.service;

import com.mozahidone.restapi.model.Book;
import com.mozahidone.restapi.model.Post;
import com.mozahidone.restapi.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {
	
	private final PostRepository postRepository;
	
	public Post getPostByBook(Book book){
		return postRepository.findByBook(book);
	}
}
