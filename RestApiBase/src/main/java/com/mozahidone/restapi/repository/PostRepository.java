package com.mozahidone.restapi.repository;

import com.mozahidone.restapi.model.Book;
import com.mozahidone.restapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{
	Post findByBook(Book book);
}