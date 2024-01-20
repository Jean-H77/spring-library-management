package org.library.springlibrarymanagement.repositories;

import org.library.springlibrarymanagement.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByTitle(String title);
}
