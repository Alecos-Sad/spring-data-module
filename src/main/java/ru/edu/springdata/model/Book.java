package ru.edu.springdata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    private Long id;
    private String name;
    private String language;
    private String category; // history, it, health etc...
}
