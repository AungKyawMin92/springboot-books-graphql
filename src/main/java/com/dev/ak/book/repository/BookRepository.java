package com.dev.ak.book.repository;

import com.dev.ak.book.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByNameContaining(Object name);

    List<Book> findByRating(Object rating);
}
