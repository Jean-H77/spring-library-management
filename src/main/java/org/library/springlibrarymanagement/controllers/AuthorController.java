package org.library.springlibrarymanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.library.springlibrarymanagement.models.AuthorModel;
import org.library.springlibrarymanagement.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{firstname}/{lastname}")
    public ResponseEntity<AuthorModel> getAuthorByFirstAndLastName(
            @PathVariable String firstname, @PathVariable String lastname) {

        return authorService.getByFirstAndLastName(firstname, lastname);
    }
}
