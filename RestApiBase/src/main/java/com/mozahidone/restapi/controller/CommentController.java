package com.mozahidone.restapi.controller;


import com.mozahidone.restapi.model.Book;
import com.mozahidone.restapi.model.Comment;
import com.mozahidone.restapi.model.Post;
import com.mozahidone.restapi.service.BookService;
import com.mozahidone.restapi.service.CommentService;
import com.mozahidone.restapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api")
public class CommentController {
	
	private final CommentService commentService;
	private final BookService bookService;
	private final PostService postService;
	
	@GetMapping("/comment/{id}")
	public ResponseEntity<List<Comment>> getAllByBook(@PathVariable Long id){
		Book book = bookService.getBookById(id);
		Post post = postService.getPostByBook(book);
		return new ResponseEntity<>(commentService.findAllByPost(post),HttpStatus.OK);
	}
	
	@GetMapping("/comment/test")
	public String test(){
		return "Hello";
	}

}
