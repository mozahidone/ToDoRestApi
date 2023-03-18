package com.mozahidone.restapi.repository;

import com.mozahidone.restapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReposity extends JpaRepository<Book, Long>{

}
