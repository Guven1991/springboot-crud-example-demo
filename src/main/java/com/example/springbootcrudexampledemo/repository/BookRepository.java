package com.example.springbootcrudexampledemo.repository;

import com.example.springbootcrudexampledemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getBooksByAuthor_Id(Long Id);
}
