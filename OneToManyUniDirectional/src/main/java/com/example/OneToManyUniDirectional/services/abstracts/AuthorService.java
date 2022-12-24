package com.example.OneToManyUniDirectional.services.abstracts;

import com.example.OneToManyUniDirectional.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO createAuthor(AuthorDTO authorDTO);
    List<AuthorDTO> getAllAuthors();
    AuthorDTO getAuthorById(Long id);
    void deleteAuthorById(Long id);
    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);
}

