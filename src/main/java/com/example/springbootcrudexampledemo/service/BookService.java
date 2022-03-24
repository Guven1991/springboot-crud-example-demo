package com.example.springbootcrudexampledemo.service;

import com.example.springbootcrudexampledemo.dto.BookDto;
import com.example.springbootcrudexampledemo.entity.Book;
import com.example.springbootcrudexampledemo.error.BookNotFoundException;
import com.example.springbootcrudexampledemo.error.BookUnSupportedFieldPatchException;
import com.example.springbootcrudexampledemo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    //    private final DozerBeanMapper dozerBeanMapper;
    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public List<BookDto> findAllBook() {
        List<Book> bookDtoList = bookRepository.findAll();
        return bookDtoList.stream().map(book ->
                dozerBeanMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    public BookDto createBook(BookDto bookDto) {

        Book book = bookRepository.save(dozerBeanMapper.map(bookDto, Book.class));
        return dozerBeanMapper.map(book, BookDto.class);
    }

    public BookDto findOneBook(Long id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(Exception::new);
        return dozerBeanMapper.map(book, BookDto.class);

//        boolean existBook = bookRepository.existsById(id);
//
//        if(!existBook){
//            throw new BookNotFoundException(id);
//        }
//        Book book = bookRepository.findById(id);
//        return dozerBeanMapper.map(book, BookDto.class);

//            Optional<Book> book = null;
//        if (!bookRepository.existsById(id)) {
//            throw new BookNotFoundException(id);
//        } else {
//            book = bookRepository.findById(id);
//            BookDto bookDozer = dozerBeanMapper.map(book, BookDto.class);
//
//            System.out.println(bookDozer);
//            return dozerBeanMapper.map(book, BookDto.class);

//        return bookRepository.findById(id)
//                .map(x -> {
//                    Book book = bookRepository.findById(id);
//                    BookDto bookDto = dozerBeanMapper.map(book, BookDto.class);
//                    return bookDto;
//
////                    if (!isBlank(author)) {
////                        x.setAuthor(author);
////                        return bookRepository.save(x);
////                    } else {
////                        throw new BookUnSupportedFieldPatchException(update.keySet());
////                    }
//                }).orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book updateBook(Book book, Long id) {
        return bookRepository.findById(id).map(x -> {
            x.setName(book.getName());
            x.setAuthor(book.getAuthor());
            x.setPrice(book.getPrice());
            return bookRepository.save(x);
        }).orElseGet(() -> {
            book.setId(id);
            return bookRepository.save(book);
        });
    }

    public Book patchAuthor(Map<String, String> update, Long id) {
        return bookRepository.findById(id)
                .map(x -> {
                    String author = update.get("author");
                    if (!isBlank(author)) {
                        x.setAuthor(author);
                        return bookRepository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }
                }).orElseThrow(() -> new BookNotFoundException(id));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


}
