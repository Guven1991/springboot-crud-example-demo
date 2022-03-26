package com.example.springbootcrudexampledemo.service;

import com.example.springbootcrudexampledemo.dto.BookDto;
import com.example.springbootcrudexampledemo.entity.Book;
import com.example.springbootcrudexampledemo.error.BookNotFoundException;
import com.example.springbootcrudexampledemo.error.BookUnSupportedFieldPatchException;
import com.example.springbootcrudexampledemo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    //    private final DozerBeanMapper dozerBeanMapper;
    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public BookDto createBook(BookDto bookDto) {

        Book book = bookRepository.save(dozerBeanMapper.map(bookDto, Book.class));
        return dozerBeanMapper.map(book, BookDto.class);
    }

    public List<BookDto> findAllBook() {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream().map(book ->
                dozerBeanMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    public BookDto findOneBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return dozerBeanMapper.map(book, BookDto.class);

    }

    public BookDto updateBook(BookDto bookDto, Long id) {
        Book book = dozerBeanMapper.map(bookDto, Book.class);
        Book updateBook = bookRepository.findById(id).map(x -> {
            x.setName(book.getName());
            x.setAuthor(book.getAuthor());
            x.setPrice(book.getPrice());
            return bookRepository.save(x);
        }).orElseGet(() -> {
            book.setId(id);
            return bookRepository.save(book);
        });

        return dozerBeanMapper.map(updateBook, BookDto.class);
    }

    public BookDto patchAuthor(Map<String, String> update, Long id) {

        Book book = bookRepository.findById(id)
                .map(x -> {
                    String author = update.get("author");
                    if (!isBlank(author)) {
                        x.setAuthor(author);
                        return bookRepository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }
                }).orElseThrow(() -> new BookNotFoundException(id));

        return dozerBeanMapper.map(book, BookDto.class);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }


}
