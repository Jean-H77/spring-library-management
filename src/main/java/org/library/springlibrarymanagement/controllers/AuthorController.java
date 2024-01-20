package org.library.springlibrarymanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.library.springlibrarymanagement.services.AuthorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

}
