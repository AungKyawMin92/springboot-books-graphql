package com.dev.ak.book.controller;

import com.dev.ak.book.model.Author;
import com.dev.ak.book.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @QueryMapping
    public List<Author> allAuthors() {
        return authorService.findAll();
    }
}
