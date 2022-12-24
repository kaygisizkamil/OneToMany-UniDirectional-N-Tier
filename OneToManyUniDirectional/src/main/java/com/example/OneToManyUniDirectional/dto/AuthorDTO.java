package com.example.OneToManyUniDirectional.dto;

import lombok.Data;

import java.util.Set;
@Data
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<BookDTO> books;
}