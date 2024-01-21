package org.library.springlibrarymanagement.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.library.springlibrarymanagement.entities.AuthorEntity;
import org.library.springlibrarymanagement.entities.BookEntity;
import org.library.springlibrarymanagement.exception.exceptions.ApiBadRequestException;
import org.library.springlibrarymanagement.exception.exceptions.ApiResourceNotFoundException;
import org.library.springlibrarymanagement.models.AuthorModel;
import org.library.springlibrarymanagement.models.BookModel;
import org.library.springlibrarymanagement.repositories.BookRepository;
import org.library.springlibrarymanagement.validators.book.BookFormValidator;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final BookFormValidator bookFormValidator;

    public BookService(BookRepository bookRepository, @Lazy BookFormValidator bookFormValidator) {
        this.bookRepository = bookRepository;
        this.bookFormValidator = bookFormValidator;
    }

    public ResponseEntity<BookModel> getBookByTitle(String title) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findByTitle(title);

        if(optionalBookEntity.isPresent()) {
            BookEntity bookEntity = optionalBookEntity.get();

            BookModel bookModel = BookModel.builder()
                    .title(bookEntity.getTitle())
                    .ISBN(bookEntity.getISBN())
                    .genreId(bookEntity.getGenreId())
                    .authors(authorEntityToModels(bookEntity.getAuthorEntities()))
                    .build();

            return new ResponseEntity<>(bookModel, HttpStatus.OK);
        }

        throw new ApiResourceNotFoundException("Cannot find book with the title = " + title);
    }

    @Transactional
    public ResponseEntity<BookModel> createBook(BookModel bookModel, BindingResult bindingResult) {

        bookFormValidator.validate(bookModel, bindingResult);

        if(bindingResult.hasErrors()) {
            throw new ApiBadRequestException("Could not complete book creation", bindingResult);
        }

        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookModel.getTitle());
        bookEntity.setISBN(bookModel.getISBN());
        bookEntity.setGenreId(bookModel.getGenreId());

        Set<AuthorEntity> authors = bookModel.getAuthors().stream()
                .map(authorModel -> {
                    AuthorEntity author = new AuthorEntity();
                    author.setFirstName(authorModel.getFirstName());
                    author.setLastName(authorModel.getLastName());
                    return author;
                })
                .collect(Collectors.toSet());

        bookEntity.addAuthorEntities(authors);

        return new ResponseEntity<>(bookEntityToModel(bookRepository.save(bookEntity)), HttpStatus.CREATED);
    }

    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public boolean existsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }

    private static BookModel bookEntityToModel(BookEntity bookEntity) {
        return BookModel
                .builder()
                .title(bookEntity.getTitle())
                .ISBN(bookEntity.getISBN())
                .genreId(bookEntity.getGenreId())
                .authors(authorEntityToModels(bookEntity.getAuthorEntities()))
                .build();
    }

    private static AuthorModel authorEntityToModels(AuthorEntity authorEntity) {
        return AuthorModel
                .builder()
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLastName())
                .build();
    }

    private static Set<AuthorModel> authorEntityToModels(Set<AuthorEntity> authorEntities) {
        return authorEntities
                .stream()
                .map(BookService::authorEntityToModels)
                .collect(Collectors.toSet());
    }
}