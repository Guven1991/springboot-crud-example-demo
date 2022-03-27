package com.example.springbootcrudexampledemo.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookCreateRequest {

    private String name;

    private AuthorCreateRequest author;

    private Double price;
}
