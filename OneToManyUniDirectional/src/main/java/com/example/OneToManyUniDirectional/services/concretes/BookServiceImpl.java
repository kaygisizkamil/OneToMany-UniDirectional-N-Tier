package com.example.OneToManyUniDirectional.services.concretes;

import com.example.OneToManyUniDirectional.domain.Book;
import com.example.OneToManyUniDirectional.dto.BookDTO;
import com.example.OneToManyUniDirectional.repository.BookRepository;
import com.example.OneToManyUniDirectional.services.abstracts.BookService;
import com.example.OneToManyUniDirectional.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        // map the DTO to a domain object
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setName(bookDTO.getName());

        // save the domain object to the database
        Book savedBook = bookRepository.save(book);

        // map the saved domain object back to a DTO
        BookDTO savedBookDTO = new BookDTO();
        savedBookDTO.setId(savedBook.getId());
        savedBookDTO.setIsbn(savedBook.getIsbn());
        savedBookDTO.setName(savedBook.getName());

        return savedBookDTO;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        // find all books in the database
        List<Book> books = bookRepository.findAll();

        // map the domain objects to DTOs
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setIsbn(book.getIsbn());
            bookDTO.setName(book.getName());

            bookDTOs.add(bookDTO);
        }

        return bookDTOs;
    }
    @Override
    public BookDTO getBookById(Long id) {
// find the book in the database
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            throw new ResourceNotFoundException("Book with id " + id + " not found");
        }
        Book book = optionalBook.get();

// map the domain object to a DTO
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setName(book.getName());

        return bookDTO;
    }

    @Override
    public void deleteBookById(Long id) {
// find the book in the database
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            throw new ResourceNotFoundException("Book with id " + id + " not found");
        }
        Book book = optionalBook.get();
// delete the book from the database
        bookRepository.delete(book);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
// find the book in the database
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            throw new ResourceNotFoundException("Book with id " + id + " not found");
        }
        Book book = optionalBook.get();
// update the book with the new values
        book.setIsbn(bookDTO.getIsbn());
        book.setName(bookDTO.getName());

// save the updated book to the database
        Book updatedBook = bookRepository.save(book);

// map the updated domain object back to a DTO
        BookDTO updatedBookDTO = new BookDTO();
        updatedBookDTO.setId(updatedBook.getId());
        updatedBookDTO.setIsbn(updatedBook.getIsbn());
        updatedBookDTO.setName(updatedBook.getName());

        return updatedBookDTO;
    }
}