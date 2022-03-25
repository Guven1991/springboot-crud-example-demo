package com.example.springbootcrudexampledemo.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BookResponse {

    private String name;

    private String author;

    private BigDecimal price;
}
