//package com.example.springbootcrudexampledemo.entity;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//public class Author {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    private String lastname;
//
//    @OneToMany(mappedBy ="author", cascade = CascadeType.REMOVE)        // mappedBy="user" Hoax tablosundakı user sütünu bizim forenkey imizdir demek
//    private List<Book> books;
//
//}
