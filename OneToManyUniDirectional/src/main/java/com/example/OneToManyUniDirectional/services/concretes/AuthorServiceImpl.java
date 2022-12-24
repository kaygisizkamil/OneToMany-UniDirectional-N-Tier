package com.example.OneToManyUniDirectional.services.concretes;

import com.example.OneToManyUniDirectional.domain.Author;
import com.example.OneToManyUniDirectional.domain.Book;
import com.example.OneToManyUniDirectional.dto.AuthorDTO;
import com.example.OneToManyUniDirectional.dto.BookDTO;
import com.example.OneToManyUniDirectional.repository.AuthorRepository;
import com.example.OneToManyUniDirectional.services.abstracts.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        // map the DTO to a domain object
        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setBooks(authorDTO.getBooks().stream().map(bookDTO -> {
            Book book = new Book();
            book.setIsbn(bookDTO.getIsbn());
            book.setName(bookDTO.getName());
            return book;
        }).collect(Collectors.toSet()));

        // save the domain object to the database
        Author savedAuthor = authorRepository.save(author);

        // map the saved domain object back to a DTO
        AuthorDTO savedAuthorDTO = new AuthorDTO();
        savedAuthorDTO.setId(savedAuthor.getId());
        savedAuthorDTO.setFirstName(savedAuthor.getFirstName());
        savedAuthorDTO.setLastName(savedAuthor.getLastName());
        savedAuthorDTO.setBooks(savedAuthor.getBooks().stream().map(book -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setIsbn(book.getIsbn());
            bookDTO.setName(book.getName());
            return bookDTO;
        }).collect(Collectors.toSet()));
        return savedAuthorDTO;
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
// find all authors in the database
        List<Author> authors = authorRepository.findAll();

// map the domain objects to DTOs
        List<AuthorDTO> authorDTOs = new ArrayList<>();
        for (Author author : authors) {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setId(author.getId());
            authorDTO.setFirstName(author.getFirstName());
            authorDTO.setLastName(author.getLastName());
            authorDTO.setBooks(author.getBooks().stream().map(book -> {
                BookDTO bookDTO = new BookDTO();
                bookDTO.setId(book.getId());
                bookDTO.setIsbn(book.getIsbn());
                bookDTO.setName(book.getName());
                return bookDTO;
            }).collect(Collectors.toSet()));

            authorDTOs.add(authorDTO);
        }
        return authorDTOs;
    }
    @Override
    public AuthorDTO getAuthorById(Long id) {
// find the author in the database
        Author author = authorRepository.findById(id).get();

// map the domain object to a DTO
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setBooks(author.getBooks().stream().map(book -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setIsbn(book.getIsbn());
            bookDTO.setName(book.getName());
            return bookDTO;
        }).collect(Collectors.toSet()));

        return authorDTO;
    }
    @Override
    public void deleteAuthorById(Long id) {
// delete the author from the database
        authorRepository.deleteById(id);
    }
    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
// find the author in the database
        Author author = authorRepository.findById(id).get();

// update the fields of the author
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setBooks(authorDTO.getBooks().stream().map(bookDTO -> {
            Book book = new Book();
            book.setIsbn(bookDTO.getIsbn());
            book.setName(bookDTO.getName());
            return book;
        }).collect(Collectors.toSet()));

// save the updated author to the database
        Author savedAuthor = authorRepository.save(author);

// map the saved domain object back to a DTO
        AuthorDTO savedAuthorDTO = new AuthorDTO();
        savedAuthorDTO.setId(savedAuthor.getId());
        savedAuthorDTO.setFirstName(savedAuthor.getFirstName());
        savedAuthorDTO.setLastName(savedAuthor.getLastName());
        savedAuthorDTO.setBooks(savedAuthor.getBooks().stream().map(book -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setIsbn(book.getIsbn());
            bookDTO.setName(book.getName());
            return bookDTO;
        }).collect(Collectors.toSet()));

        return savedAuthorDTO;
    }


}
