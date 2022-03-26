package service;

import com.example.springbootcrudexampledemo.dto.BookDto;
import com.example.springbootcrudexampledemo.entity.Book;
import com.example.springbootcrudexampledemo.repository.BookRepository;
import com.example.springbootcrudexampledemo.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private BookDto bookDto;
    private Book book;
    @Before
    public void init(){

        book = Book.builder()
                .id(1L)
                .name("ali ata bak")
                .author("ali")
                .price(BigDecimal.valueOf(10))
                .build();

        bookDto =BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .price(book.getPrice())
                .build();
    }

    @Test
    public void  createBook(){

        Mockito.when(bookRepository.save(any())).thenReturn(book);
        BookDto bookDtoReturned = bookService.createBook(bookDto);
        assertEquals(Optional.of(1L),Optional.ofNullable(bookDtoReturned.getId()));
        assertEquals("ali ata bak",bookDtoReturned.getName());
    }



}
