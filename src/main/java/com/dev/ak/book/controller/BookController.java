package com.dev.ak.book.controller;


import com.dev.ak.book.model.Book;
import com.dev.ak.book.service.BookService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    // REST API
    @PostMapping("/book/add")
    public String addBooks(@RequestBody List<Book> books) {
        bookService.addBook(books);
        return "record inserted " + books.size();
    }

    // graphQL API
    @SchemaMapping(typeName = "Query",value = "allBooks")
    public List<Book> findAll() {
        return bookService.findAll();
    }
    // graphQL API
    @QueryMapping
    public Book findOne(@Argument Integer bookId) {
        return bookService.findOne(bookId);
    }
    // REST API
    @PostMapping("/filterBook")
    public ResponseEntity<Object> fileterBook(@RequestBody String query) {
        ExecutionResult result = bookService.getFilteredBook(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

}
