package org.library.springlibrarymanagement.services;

import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.library.springlibrarymanagement.entities.AuthorEntity;
import org.library.springlibrarymanagement.exception.exceptions.ApiResourceNotFoundException;
import org.library.springlibrarymanagement.models.AuthorModel;
import org.library.springlibrarymanagement.models.BookModel;
import org.library.springlibrarymanagement.repositories.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public ResponseEntity<AuthorModel> getByFirstAndLastName(String firstName, String lastName) {
        Optional<AuthorEntity> authorEntityOptional = authorRepository.findByFirstNameAndLastName(firstName, lastName);

        if(authorEntityOptional.isPresent()) {
            AuthorModel authorModel = authorEntityToModel(authorEntityOptional.get());
            return new ResponseEntity<>(authorModel, HttpStatus.OK);
        }

        throw new ApiResourceNotFoundException("Cannot find author with first name = " + firstName + " and last name = " + lastName);
    }

    public static AuthorModel authorEntityToModel(AuthorEntity authorEntity) {
        return AuthorModel
                .builder()
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLastName())
                .books(
                        Sets.newHashSet((BookModel) authorEntity.getBookEntities()
                                .stream()
                                .map(book -> BookModel.builder()
                                        .title(book.getTitle())
                                        .title(book.getTitle())
                                        .genreId(book.getGenreId())
                                        .build()))
                )
                .build();
    }

    public static Set<AuthorModel> authorEntityToModels(Set<AuthorEntity> authorEntities) {
        return authorEntities
                .stream()
                .map(AuthorService::authorEntityToModel)
                .collect(Collectors.toSet());
    }
}
