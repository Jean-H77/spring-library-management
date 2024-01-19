package org.library.springlibrarymanagement.services;

import lombok.RequiredArgsConstructor;
import org.library.springlibrarymanagement.entities.BookEntity;
import org.library.springlibrarymanagement.models.BookModel;
import org.library.springlibrarymanagement.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookModel getBookByTitle(String title) {
        Optional<BookEntity> bookEntity = bookRepository.findByTitle(title);
        if(bookEntity.isPresent()) {
            return toModel(bookEntity.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with title " + title + " has not been found");
    }

    public void createBook(BookModel bookModel) {

    }

    private static BookModel toModel(BookEntity bookEntity) {
        return BookModel.builder()
                .title(bookEntity.getTitle())
                .ISBN(bookEntity.getISBN())
                .build();
    }

    private static BookEntity toEntity(BookModel bookModel) {
        return BookEntity.builder()
                .title(bookModel.getTitle())
                .ISBN(bookModel.getISBN())
                .build();
    }
}
