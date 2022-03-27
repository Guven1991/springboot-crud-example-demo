package com.example.springbootcrudexampledemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    @OneToMany(mappedBy ="author", cascade = CascadeType.REMOVE)        // mappedBy="user" Hoax tablosundakı user sütünu bizim forenkey imizdir demek
    private List<Book> books;

}
