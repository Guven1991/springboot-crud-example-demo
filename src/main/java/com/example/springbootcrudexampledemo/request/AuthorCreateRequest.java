package com.example.springbootcrudexampledemo.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorCreateRequest {

    private Long id;

    private String name;

    private String lastname;

}
