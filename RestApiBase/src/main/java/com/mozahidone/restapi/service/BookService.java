package com.mozahidone.restapi.service;

import com.mozahidone.restapi.model.Book;
import com.mozahidone.restapi.repository.BookReposity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
	
	private final BookReposity bookReposity;
	
	public Book getBookById(Long id){
		return bookReposity.findById(id).orElse(null);
	}

}
