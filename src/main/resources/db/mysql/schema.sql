CREATE TABLE IF NOT EXISTS books (
  id     INT(5) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  title   varchar(50) NOT NULL,
  ISBN   varchar(255) NOT NULL,
  INDEX (title)
);

CREATE TABLE IF NOT EXISTS authors (
  id         INT(4) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(50) NOT NULL,
  last_name  varchar(50) NOT NULL,
  book_id    INT(5) UNSIGNED,
  FOREIGN KEY (book_id) references books(id)
);

CREATE TABLE IF NOT EXISTS book_authors (
    book_id    INT(5) UNSIGNED NOT NULL,
    author_id  INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);