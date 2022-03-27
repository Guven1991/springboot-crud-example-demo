package service;

import com.example.springbootcrudexampledemo.dto.AuthorDto;
import com.example.springbootcrudexampledemo.dto.BookDto;
import com.example.springbootcrudexampledemo.entity.Author;
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

    private Author author;

    private AuthorDto authorDto;

    @Before
    public void init(){

        author = Author.builder()
                .id(1L)
                .name("guven")
                .lastname("ayvaz")
                .build();

        authorDto = AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .lastname(author.getLastname())
                .build();

        book = Book.builder()
                .id(1L)
                .name("ali ata bak")
                .author(author)
                .price(50.05)
                .build();

        bookDto =BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(authorDto)
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
        assertEquals("guven", bookDtoList.get(0).getAuthor().getName());

       }

    @Test
    public void findOneBook(){
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(book));
        BookDto bookDtoReturned = bookService.findOneBook(1L);
        assertEquals("guven", bookDtoReturned.getAuthor().getName());

    }

    @Test
    public void updateBook(){
        book.setPrice(50.05);
        bookDto.setPrice(50.05);

        when(bookRepository.save(any())).thenReturn(book);
        BookDto bookDtoReturned = bookService.updateBook(bookDto,1L);
        assertEquals(Optional.of(50.05),Optional.ofNullable(bookDtoReturned.getPrice()));

    }

    @Test
    public void patchPrice(){
        book.setPrice(50.05);
        bookDto.setPrice(50.05);

        Map<Double, Double> update = new HashMap<>();
        update.put(50.04, 50.05);

        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(book));
        when(bookRepository.save(any())).thenReturn(book);

        BookDto bookDtoReturned = bookService.patchPrice(update,1L);
        assertEquals(Optional.of(50.05),Optional.ofNullable(bookDtoReturned.getPrice()));

    }

    @Test
    public void deleteBook(){
        when(bookRepository.existsById(any())).thenReturn(true);
        bookService.deleteBook(1L);
        verify(bookRepository).deleteById(1L);
    }





}
