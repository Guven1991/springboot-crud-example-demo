package com.example.springbootcrudexampledemo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookAuthorResponse {

    private Long id;

    private String name;
//
//    private AuthorResponse author;

    private Double price;
}
