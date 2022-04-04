package service;

import com.example.springbootcrudexampledemo.dto.AuthorDto;
import com.example.springbootcrudexampledemo.dto.BookDto;
import com.example.springbootcrudexampledemo.entity.Author;
import com.example.springbootcrudexampledemo.entity.Book;
import com.example.springbootcrudexampledemo.repository.AuthorRepository;
import com.example.springbootcrudexampledemo.service.AuthorService;
import com.example.springbootcrudexampledemo.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookService bookService;

    @InjectMocks
    @Spy
    private AuthorService authorService;

    private BookDto bookDto;

    private Book book;

    private Author author;

    private AuthorDto authorDto;

    @Before
    public void init() {

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

        bookDto = BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(authorDto)
                .price(book.getPrice())
                .build();
    }

    @Test
    public void createAuthor() {
        when(authorRepository.save(any())).thenReturn(author);

        AuthorDto authorDtoReturned = authorService.createAuthor(authorDto);

        assertEquals(Optional.of(1L), Optional.ofNullable(authorDtoReturned.getId()));
        assertEquals("guven", authorDtoReturned.getName());
    }

    @Test
    public void getAllAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(author));
        List<AuthorDto> authorDtoList = authorService.getAllAuthors();
        assertEquals(1, authorDtoList.size());
        assertEquals("guven", authorDtoList.get(0).getName());
    }

    @Test
    public void getAuthorById() {
        when(authorRepository.findById(any())).thenReturn(Optional.ofNullable(author));
        when(bookService.getAllBooksByAuthorId(any())).thenReturn(List.of(bookDto));
        AuthorDto authorDto = authorService.getAuthorById(1L);
        assertEquals(1L, authorDto.getId().longValue());
        assertEquals("guven", authorDto.getName());
    }

//    @Test
//    public void updateAuthor() {
////        when(authorRepository.findById(any())).thenReturn(Optional.of(author));
//        when(authorRepository.save(any())).thenReturn(author);
//        assertEquals(1L, authorDto.getId().longValue());
//        assertEquals("guven", authorDto.getName());
//    }

//    @Test
//    public void deleteAuthor() {
////        when(authorRepository.existsById(any())).thenReturn(true);
//        authorRepository.deleteById(1L);
//        verify(authorRepository).deleteById(1L);
//    }


//    @Test
//    public void findOneBook() {
//        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(book));
//        BookDto bookDtoReturned = bookService.findOneBook(1L);
//        assertEquals("guven", bookDtoReturned.getAuthor().getName());
//
//    }
//
//    @Test
//    public void updateBook() {
//        book.setPrice(50.05);
//        bookDto.setPrice(50.05);
//
//        when(bookRepository.save(any())).thenReturn(book);
//        BookDto bookDtoReturned = bookService.updateBook(bookDto, 1L);
//        assertEquals(Optional.of(50.05), Optional.ofNullable(bookDtoReturned.getPrice()));
//
//    }
//
//    @Test
//    public void patchPrice() {
//        book.setPrice(50.05);
//        bookDto.setPrice(50.05);
//
//        Map<Double, Double> update = new HashMap<>();
//        update.put(50.04, 50.05);
//
//        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(book));
//        when(bookRepository.save(any())).thenReturn(book);



}
