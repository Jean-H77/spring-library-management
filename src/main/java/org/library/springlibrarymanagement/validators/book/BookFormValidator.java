package org.library.springlibrarymanagement.validators.book;

import org.library.springlibrarymanagement.models.BookModel;
import org.library.springlibrarymanagement.services.BookService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookFormValidator implements Validator {

    private final BookService bookService;

    public BookFormValidator(@Lazy BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BookModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(target instanceof BookModel bookModel) {

            String title = bookModel.getTitle();

            if(bookService.existsByTitle(title)) {
                errors.reject("exists","Book already in database");
                return;
            }

            if(title.length() > 50) {
                errors.reject("title", "book title must not be over 50 characters in length.");
            }

            if(bookModel.getISBN().length() > 255) {
                errors.reject("ISBN", "ISBN must not be over 255 characters in length.");
            }

            if(bookModel.getGenreId() < 1) {
                errors.reject("GenreId", "GenreId must be greater than 0");
            }

            if(bookModel.getAuthors().isEmpty()) {
                errors.reject("authors", "Authors must not be empty");
            }
        }
    }
}
