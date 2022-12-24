package com.example.OneToManyUniDirectional.services.abstracts;

import com.example.OneToManyUniDirectional.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    void deleteBookById(Long id);
    BookDTO updateBook(Long id, BookDTO bookDTO);
}