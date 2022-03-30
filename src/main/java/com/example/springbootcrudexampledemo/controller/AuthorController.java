package com.example.springbootcrudexampledemo.controller;

import com.example.springbootcrudexampledemo.dto.AuthorDto;
import com.example.springbootcrudexampledemo.request.AuthorCreateRequest;
import com.example.springbootcrudexampledemo.response.AuthorGetByIdResponse;
import com.example.springbootcrudexampledemo.response.AuthorResponse;
import com.example.springbootcrudexampledemo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> findAll() {
        List<AuthorDto> authorDtoList = authorService.findAll();
        List<AuthorResponse> authorResponseList =  authorDtoList.stream().map(authorDto ->
                dozerBeanMapper.map(authorDto,AuthorResponse.class)).collect(Collectors.toList());
        return ResponseEntity.ok(authorResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorGetByIdResponse> getAuthorById(@PathVariable Long id) {
        AuthorDto authorDto = null;
        try {
            authorDto = authorService.getAuthorById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(dozerBeanMapper.map(authorDto, AuthorGetByIdResponse.class));
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorCreateRequest authorCreateRequest) {

        AuthorDto authorDto = authorService.createAuthor(dozerBeanMapper.map(authorCreateRequest, AuthorDto.class));
        return ResponseEntity.ok(dozerBeanMapper.map(authorDto, AuthorResponse.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateBook(@RequestBody AuthorCreateRequest authorCreateRequest, @PathVariable Long id) {

        AuthorDto authorDto = dozerBeanMapper.map(authorCreateRequest, AuthorDto.class);
        AuthorDto authorUpdatedDto = authorService.updateAuthor(authorDto, id);
        return ResponseEntity.ok(dozerBeanMapper.map(authorUpdatedDto, AuthorResponse.class));

    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);

    }



}
