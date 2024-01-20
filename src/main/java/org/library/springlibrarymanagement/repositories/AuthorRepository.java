package org.library.springlibrarymanagement.repositories;

import org.library.springlibrarymanagement.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    List<AuthorEntity> findAllByFirstNameInAndLastNameIn(List<String> firstName, List<String> lastName);
}
