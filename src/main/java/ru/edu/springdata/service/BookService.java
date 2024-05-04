package ru.edu.springdata.service;

import ru.edu.springdata.model.Book;

import java.util.List;

public interface BookService {
    Book getById(Long id);

    List<Book> getAll();

    Book save(Book book);

    Book update(Book book);

    void delete(Long id);

    List<Book> getByCategoryId(Long id);

    List<Book> getByLanguage(String language);

    List<Book> getByLanguageAndCategory(String language, String category);
}
