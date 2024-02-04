package org.library.springlibrarymanagement.repositories;

import org.library.springlibrarymanagement.entities.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
