package org.library.springlibrarymanagement.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books", uniqueConstraints={@UniqueConstraint(columnNames = {"title" , "ISBN"})})
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "genre_id")
    private int genreId;

    @ManyToMany(fetch = FetchType.LAZY, cascade =  {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<AuthorEntity> authorEntities = new HashSet<>();

    public void addAuthorEntity(AuthorEntity authorEntity) {
        authorEntities.add(authorEntity);
        authorEntity.getBookEntities().add(this);
    }

    public void addAuthorEntities(Set<AuthorEntity> authorEntities) {
        authorEntities.forEach(this::addAuthorEntity);
    }
}
