package com.example.springbootcrudexampledemo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BookResponse {

    private Long id;

    private String name;

    private String author;

    private BigDecimal price;
}
