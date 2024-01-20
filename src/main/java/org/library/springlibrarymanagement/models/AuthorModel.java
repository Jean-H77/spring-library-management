package org.library.springlibrarymanagement.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthorModel {

    private String firstName;

    private String lastName;
}
