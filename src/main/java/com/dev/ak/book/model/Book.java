package com.dev.ak.book.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    private int bookId;
    private String name;
    private int rating;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="authorId")
    private Author author;

}
