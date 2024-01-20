package org.library.springlibrarymanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.library.springlibrarymanagement.models.BookModel;
import org.library.springlibrarymanagement.services.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{title}")
    public BookModel getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @DeleteMapping("/{title}")
    public void deleteBookByTitle(@PathVariable String title) {
        bookService.deleteBookByTitle(title);
    }

    @PostMapping
    public void createBook(@RequestBody BookModel bookModel) {
        bookService.createBook(bookModel);
    }
}
