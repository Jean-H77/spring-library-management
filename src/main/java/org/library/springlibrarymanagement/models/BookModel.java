package org.library.springlibrarymanagement.models;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class BookModel {

    private String title;

    private String ISBN;

    private Set<AuthorModel> authors;

    private int genreId;
}
