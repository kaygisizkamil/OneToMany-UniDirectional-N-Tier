package com.example.OneToManyUniDirectional.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String isbn;
    private String name;
}