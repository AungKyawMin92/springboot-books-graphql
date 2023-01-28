package com.dev.ak.book.service;

import com.dev.ak.book.model.Author;
import com.dev.ak.book.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
