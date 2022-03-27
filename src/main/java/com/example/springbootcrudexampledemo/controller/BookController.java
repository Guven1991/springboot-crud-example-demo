package com.example.springbootcrudexampledemo.controller;

import com.example.springbootcrudexampledemo.dto.BookDto;
import com.example.springbootcrudexampledemo.request.BookCreateRequest;
import com.example.springbootcrudexampledemo.response.BookResponse;
import com.example.springbootcrudexampledemo.service.BookService;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
    private final BookService bookService;

    public BookController(BookService bookService) {

        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll() {
        List<BookDto> bookDtoList = bookService.findAllBook();
        List<BookResponse> bookResponseList =  bookDtoList.stream().map(bookDto ->
                dozerBeanMapper.map(bookDto,BookResponse.class)).collect(Collectors.toList());
        return ResponseEntity.ok(bookResponseList);
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookCreateRequest bookCreateRequest) {

        BookDto bookDto = bookService.createBook(dozerBeanMapper.map(bookCreateRequest, BookDto.class));
        return ResponseEntity.ok(dozerBeanMapper.map(bookDto, BookResponse.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findOneBook(@PathVariable Long id) {
        BookDto bookDto = null;
        try {
            bookDto = bookService.findOneBook(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(dozerBeanMapper.map(bookDto, BookResponse.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@RequestBody BookCreateRequest bookCreateRequest, @PathVariable Long id) {

        BookDto bookDto = dozerBeanMapper.map(bookCreateRequest, BookDto.class);
        BookDto bookUpdatedDto = bookService.updateBook(bookDto, id);
        return ResponseEntity.ok(dozerBeanMapper.map(bookUpdatedDto, BookResponse.class));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> patchPrice(@RequestBody Map<Double, Double> update, @PathVariable Long id) {
        BookDto bookDto = bookService.patchPrice(update, id);
        return ResponseEntity.ok(dozerBeanMapper.map(bookDto, BookResponse.class));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);

    }

}
