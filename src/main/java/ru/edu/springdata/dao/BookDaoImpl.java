package ru.edu.springdata.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.edu.springdata.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    private static final String FIND_ALL = "SELECT * FROM books";
    private static final String FIND_BY_ID = "SELECT * FROM books WHERE id = :id";
    private static final String FIND_BY_NAME = "SELECT * FROM books WHERE name = :name";
    private static final String FIND_BY_CATEGORY = "SELECT * FROM books WHERE category = :category";
    private static final String FIND_BY_LANGUAGE = "SELECT * FROM books WHERE language = :language";
    private static final String UPDATE = "UPDATE books SET name = :name, language = :language, category = :category WHERE id = :id";
    private static final String DELETE = "DELETE FROM books WHERE id = :id";

    private final SimpleJdbcInsert jdbcInsert;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Qualifier("bookRowMapper")
    private final BeanPropertyRowMapper<Book> bookMapper;
    private final MapSqlParameterSource mapSqlParameterSource;

    public BookDaoImpl(SimpleJdbcInsert jdbcInsert,
                       NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                       BeanPropertyRowMapper<Book> bookMapper,
                       MapSqlParameterSource mapSqlParameterSource) {
        this.jdbcInsert = jdbcInsert;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.bookMapper = bookMapper;
        this.mapSqlParameterSource = mapSqlParameterSource;
    }

    @Override
    public List<Book> findAll() {
        return namedParameterJdbcTemplate.query(FIND_ALL, bookMapper);
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                FIND_BY_ID,
                mapSqlParameterSource.addValue("id", id),
                bookMapper)
        );
    }

    @Override
    public List<Book> findByName(String name) {
        return namedParameterJdbcTemplate.query(
                FIND_BY_NAME,
                mapSqlParameterSource.addValue("name", name),
                bookMapper);
    }

    @Override
    public List<Book> findByCategory(String category) {
        return namedParameterJdbcTemplate.query(
                FIND_BY_CATEGORY,
                mapSqlParameterSource.addValue("category", category),
                bookMapper);
    }

    @Override
    public List<Book> findByLanguage(String language) {
        return namedParameterJdbcTemplate.query(
                FIND_BY_LANGUAGE,
                mapSqlParameterSource.addValue("language", language),
                bookMapper
        );
    }

    @Override
    public Long save(Book book) {
        return jdbcInsert.executeAndReturnKey(
                new BeanPropertySqlParameterSource(book)
        ).longValue();
    }

    @Override
    public int update(Book book) {
        return namedParameterJdbcTemplate.update(
                UPDATE,
                new BeanPropertySqlParameterSource(book)
        );
    }

    @Override
    public int deleteById(long id) {
        return namedParameterJdbcTemplate.update(
                DELETE,
                mapSqlParameterSource.addValue("id", id)
        );
    }
}
