package com.example.springbootcrudexampledemo.repository;

import com.example.springbootcrudexampledemo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
