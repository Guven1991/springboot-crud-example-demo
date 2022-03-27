package com.example.springbootcrudexampledemo.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookUpdateRequest {

    private String name;

    private AuthorUpdateRequest author;

    private Double price;
}
