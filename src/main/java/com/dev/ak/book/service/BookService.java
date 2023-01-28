package com.dev.ak.book.service;

import com.dev.ak.book.model.Book;
import graphql.ExecutionResult;

import java.util.List;

public interface BookService {
    void addBook(List<Book> books);

    List<Book> findAll();

    Book findOne(Integer id);


    ExecutionResult getFilteredBook(String query);
}
