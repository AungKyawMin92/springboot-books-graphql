package com.dev.ak.book.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Author {

    @Id
    private int authorId;
    private String firstName;
    private String lastName;

}
