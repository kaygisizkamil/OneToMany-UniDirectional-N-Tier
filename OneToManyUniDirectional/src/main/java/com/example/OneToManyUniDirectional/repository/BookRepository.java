package com.example.OneToManyUniDirectional.repository;

import com.example.OneToManyUniDirectional.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findById(long id);
    List<Book> findAll();
    Book save(Book book);
    void deleteById(long id);
}