package ru.edu.springdata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Сущность описывающая книги.
 * Между книгами и категориями связь один ко многим.
 * Книга может быть только в одной категории. В одной категории может быть множество книг.
 * <p>
 * Между книгами и авторами связь многие ко многим.
 * Между авторами и адресами свзяь один к одному
 */

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String language;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();
}

