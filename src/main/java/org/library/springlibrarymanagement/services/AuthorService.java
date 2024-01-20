package org.library.springlibrarymanagement.services;

import lombok.RequiredArgsConstructor;
import org.library.springlibrarymanagement.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

}
