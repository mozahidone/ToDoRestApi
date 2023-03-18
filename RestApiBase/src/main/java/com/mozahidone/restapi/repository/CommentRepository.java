package com.mozahidone.restapi.repository;

import com.mozahidone.restapi.model.Comment;
import com.mozahidone.restapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findAllByPost(Post post);
}
