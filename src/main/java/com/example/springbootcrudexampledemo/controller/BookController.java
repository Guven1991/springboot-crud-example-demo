package com.example.springbootcrudexampledemo.controller;

import com.example.springbootcrudexampledemo.dto.BookDto;
import com.example.springbootcrudexampledemo.entity.Book;
import com.example.springbootcrudexampledemo.error.BookNotFoundException;
import com.example.springbootcrudexampledemo.error.BookUnSupportedFieldPatchException;
import com.example.springbootcrudexampledemo.repository.BookRepository;
import com.example.springbootcrudexampledemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.apache.logging.log4j.util.Strings.isBlank;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;



    @GetMapping
    public List<BookDto> findAll(){
        return bookService.findAllBook();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto){
        return bookService.createBook(bookDto);
    }

    @GetMapping("/{id}")
    public BookDto findOneBook(@PathVariable Long id){
        BookDto bookDto = null;
        try {
            bookDto = bookService.findOneBook(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookDto;
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book,@PathVariable Long id){
        return bookService.updateBook(book,id);

    }

    @PatchMapping("/{id}")
    public Book patchAuthor(@RequestBody Map<String, String> update, @PathVariable Long id){
        return bookService.patchAuthor(update, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);

    }

}
