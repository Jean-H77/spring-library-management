package org.library.springlibrarymanagement.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookModel {

    private String title;

    private String ISBN;
}
