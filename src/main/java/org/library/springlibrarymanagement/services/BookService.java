package org.library.springlibrarymanagement.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.library.springlibrarymanagement.entities.AuthorEntity;
import org.library.springlibrarymanagement.entities.BookEntity;
import org.library.springlibrarymanagement.models.BookModel;
import org.library.springlibrarymanagement.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;
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

        bookEntity.addAuthorEntities(authors);

        bookRepository.save(bookEntity);
    }
}