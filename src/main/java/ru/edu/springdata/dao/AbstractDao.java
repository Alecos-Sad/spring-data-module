package ru.edu.springdata.dao;


import java.util.List;
import java.util.Optional;

public interface AbstractDao<T> {

    List<T> findAll();
    Optional<T> findById(long id);
    List<T> findByName(String name);
    List<T> findByCategory(String category);
    List<T> findByLanguage(String language);
    Long save(T t);
    int update(T t);
    int deleteById(long id);
}
