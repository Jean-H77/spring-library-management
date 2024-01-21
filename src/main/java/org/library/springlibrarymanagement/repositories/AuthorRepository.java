package org.library.springlibrarymanagement.repositories;

import org.library.springlibrarymanagement.entities.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    List<AuthorEntity> findAllByFirstNameInAndLastNameIn(List<String> firstName, List<String> lastName);
}
