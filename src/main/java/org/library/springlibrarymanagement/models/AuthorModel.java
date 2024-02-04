package org.library.springlibrarymanagement.models;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class AuthorModel {

    private String firstName;

    private String lastName;

    private Set<BookModel> books;
}
