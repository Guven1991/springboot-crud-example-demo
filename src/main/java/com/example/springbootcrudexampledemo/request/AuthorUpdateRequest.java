package com.example.springbootcrudexampledemo.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorUpdateRequest {

    private String name;

    private String lastname;

}
