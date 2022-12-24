package com.example.OneToManyUniDirectional.repository;

import com.example.OneToManyUniDirectional.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findById(long id);
    List<Author> findAll();
    Author save(Author author);
    void deleteById(long id);
}