package ru.edu.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book with " + id + " not found")
        );
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.findById(bookRepository.save(book).getId())
                .orElseThrow(() -> new RuntimeException("Book don't saved."));
    }

    //Хотел сделать dto на обновление, но у книги ничего менятся не может кроме id мб, поэтому оставил так
    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getByCategoryId(Long id) {
        return bookRepository.findByCategoryId(id);
    }

    @Override
    public List<Book> getByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    @Override
    public List<Book> getByLanguageAndCategory(String language, String category) {
        return bookRepository.findByLanguageAndCategoryName(language, category);
    }
}
