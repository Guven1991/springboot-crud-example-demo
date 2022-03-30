package com.example.springbootcrudexampledemo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author", schema="public")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

//    @JsonManagedReference
//    @OneToMany(mappedBy ="author", cascade = CascadeType.REMOVE)        // mappedBy="user" Hoax tablosundakı user sütünu bizim forenkey imizdir demek
//    private List<Book> books;

}
