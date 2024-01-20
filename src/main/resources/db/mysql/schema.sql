CREATE TABLE IF NOT EXISTS books (
  id     INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title  varchar(50) NOT NULL,
  ISBN   varchar(255) NOT NULL,
  INDEX title_index (title),
  UNIQUE (title, ISBN)
);

CREATE TABLE IF NOT EXISTS authors (
  id         INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(50) NOT NULL,
  last_name  varchar(50) NOT NULL,
  UNIQUE (first_name, last_name)
);

CREATE TABLE IF NOT EXISTS book_authors (
    book_id    INTEGER UNSIGNED NOT NULL,
    author_id  INTEGER UNSIGNED NOT NULL,
    PRIMARY KEY (book_id, author_id),
    CONSTRAINT FOREIGN KEY (book_id) REFERENCES books(id),
    CONSTRAINT FOREIGN KEY (author_id) REFERENCES authors(id)
);