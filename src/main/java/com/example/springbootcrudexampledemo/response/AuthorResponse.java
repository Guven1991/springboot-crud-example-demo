package com.example.springbootcrudexampledemo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthorResponse {

    private Long id;

    private String name;

    private String lastname;

    private List<BookResponse> books;


}
