package com.dev.ak.book.repository;

import com.dev.ak.book.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
