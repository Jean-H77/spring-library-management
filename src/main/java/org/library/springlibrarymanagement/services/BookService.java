package org.library.springlibrarymanagement.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.springlibrarymanagement.entities.AuthorEntity;
import org.library.springlibrarymanagement.entities.BookEntity;
import org.library.springlibrarymanagement.models.AuthorModel;
import org.library.springlibrarymanagement.models.BookModel;
import org.library.springlibrarymanagement.repositories.AuthorRepository;
import org.library.springlibrarymanagement.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookModel getBookByTitle(String title) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findByTitle(title);

        if(optionalBookEntity.isPresent()) {
            BookEntity bookEntity = optionalBookEntity.get();
            BookModel.builder()
                    .title(bookEntity.getTitle())
                    .ISBN(bookEntity.getISBN())
                    .build();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with title " + title + " has not been found");
    }

    @Transactional
    public void createBook(BookModel bookModel) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookModel.getTitle());
        bookEntity.setISBN(bookModel.getISBN());

        Set<AuthorEntity> authors = bookModel.getAuthors().stream()
                .map(authorModel -> {
                    AuthorEntity author = new AuthorEntity();
                    author.setFirstName(authorModel.getFirstName());
                    author.setLastName(authorModel.getLastName());
                    return author;
                })
                .collect(Collectors.toSet());

        bookEntity.getAuthorEntities().addAll(authors);

        bookRepository.save(bookEntity);
    }
}












/*Set<AuthorModel> authorModels = bookModel.getAuthors();
        Set<AuthorEntity> entities = new HashSet<>();
        authorModels.forEach(authorModel ->
                entities.add(authorRepository.save(
                        AuthorEntity.builder().
                        firstName(authorModel.getFirstName())
                        .lastName(authorModel.getLastName())
                        .build())
        ));

        entities.forEach(bookEntity::addAuthorEntity);*/