package org.library.springlibrarymanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.library.springlibrarymanagement.models.BookModel;
import org.library.springlibrarymanagement.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{title}")
    public ResponseEntity<BookModel> getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @PostMapping
    public ResponseEntity<BookModel> createBook(@RequestBody BookModel bookModel, BindingResult bindingResult) {
        return bookService.createBook(bookModel, bindingResult);
    }
}
