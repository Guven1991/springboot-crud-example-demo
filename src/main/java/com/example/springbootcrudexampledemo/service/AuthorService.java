package com.example.springbootcrudexampledemo.service;

import com.example.springbootcrudexampledemo.dto.AuthorDto;
import com.example.springbootcrudexampledemo.dto.BookDto;
import com.example.springbootcrudexampledemo.entity.Author;
import com.example.springbootcrudexampledemo.error.AuthorNotFoundException;
import com.example.springbootcrudexampledemo.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private final AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

    public List<AuthorDto> findAll() {
        List<Author> authorList = authorRepository.findAll();
        return authorList.stream().map(author ->
                dozerBeanMapper.map(author, AuthorDto.class)).collect(Collectors.toList());

    }

    public AuthorDto createAuthor(AuthorDto authorDto) {

        Author author = authorRepository.save(dozerBeanMapper.map(authorDto, Author.class));
        return dozerBeanMapper.map(author, AuthorDto.class);
    }

    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        List<BookDto> bookList = bookService.getAllBooksByAuthorId(id);

        AuthorDto authorDto = dozerBeanMapper.map(author, AuthorDto.class);

        authorDto.setBookList(bookList);

        return authorDto;
    }

    public AuthorDto updateAuthor(AuthorDto authorDto, Long id) {

        Author author = dozerBeanMapper.map(authorDto, Author.class);
        Author updateAuthor = authorRepository.findById(id).map(x -> {
            x.setName(author.getName());
            x.setLastname(author.getLastname());
            return authorRepository.save(x);
        }).orElseGet(() -> {
            author.setId(id);
            return authorRepository.save(author);
        });

        return dozerBeanMapper.map(updateAuthor, AuthorDto.class);
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }
        authorRepository.deleteById(id);
    }
}
