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
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    @Spy
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

    @Test
    public void  findAllBook(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        List<BookDto> bookDtoList = bookService.findAllBook();

        assertEquals(1,bookDtoList.size());
        assertEquals("ali", bookDtoList.get(0).getAuthor());

       }

    @Test
    public void findOneBook(){
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(book));
        BookDto bookDtoReturned = bookService.findOneBook(1L);
        assertEquals("ali", bookDtoReturned.getAuthor());

    }

    @Test
    public void updateBook(){
        book.setAuthor("veli");
        bookDto.setAuthor("veli");

        when(bookRepository.save(any())).thenReturn(book);
        BookDto bookDtoReturned = bookService.updateBook(bookDto,1L);
        assertEquals("veli",bookDtoReturned.getAuthor());

    }

    @Test
    public void patchAuthor(){
        book.setAuthor("veli");
        bookDto.setAuthor("veli");

        Map<String, String> update = new HashMap<>();
        update.put("author", "veli");

        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(book));
        when(bookRepository.save(any())).thenReturn(book);

        BookDto bookDtoReturned = bookService.patchAuthor(update,1L);
        assertEquals("veli",bookDtoReturned.getAuthor());

    }

    @Test
    public void deleteBook(){
        when(bookRepository.existsById(any())).thenReturn(true);
        bookService.deleteBook(1L);
        verify(bookRepository).deleteById(1L);
    }





}
