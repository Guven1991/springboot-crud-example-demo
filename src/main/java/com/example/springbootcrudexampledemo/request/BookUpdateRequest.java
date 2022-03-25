package com.example.springbootcrudexampledemo.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BookUpdateRequest {

    private String name;

    private String author;

    private BigDecimal price;
}
