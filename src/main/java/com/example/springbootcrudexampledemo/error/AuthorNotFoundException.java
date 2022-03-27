package com.example.springbootcrudexampledemo.error;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(Long id){

        super("Author not found : " + id);
    }
}
