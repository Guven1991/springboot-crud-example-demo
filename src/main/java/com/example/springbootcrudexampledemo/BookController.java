package com.example.springbootcrudexampledemo;

import antlr.StringUtils;
import com.example.springbootcrudexampledemo.error.BookNotFoundException;
import com.example.springbootcrudexampledemo.error.BookUnSupportedFieldPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static antlr.StringUtils.*;
import static org.apache.logging.log4j.util.Strings.isBlank;

@RestController
@RequestMapping
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/books")
    public Book newBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @GetMapping("/books/{id}")
    public Book findOneBook(@PathVariable Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@RequestBody Book book,@PathVariable Long id){
        return bookRepository.findById(id)
                .map(x -> {
                    x.setName(book.getName());
                    x.setAuthor(book.getAuthor());
                    x.setPrice(book.getPrice());
                    return bookRepository.save(x);
                })
                .orElseGet(() ->{
                    book.setId(id);
                    return  bookRepository.save(book);
                });

    }

    @PatchMapping("/books/{id}")
    public Book patchAuthor(@RequestBody Map<String, String> update, @PathVariable Long id){
        return   bookRepository.findById(id)
                .map(x ->{
                    String author = update.get("author");
                    if(!isBlank(author)){
                        x.setAuthor(author);
                        return bookRepository.save(x);
                    }else{
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }
                })
                .orElseGet(() -> {
                    throw new BookNotFoundException(id);
                });
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id){
        bookRepository.deleteById(id);

    }

}
