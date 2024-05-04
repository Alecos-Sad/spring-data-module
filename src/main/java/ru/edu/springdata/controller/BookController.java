package ru.edu.springdata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.edu.springdata.dto.BookReq;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @PostMapping("/book")
    public Book saveBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PostMapping("/update/book")
    public Book updateBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/language/{language}")
    public List<Book> getByLanguage(@PathVariable String language) {
        return bookService.getByLanguage(language);
    }

    @GetMapping("/get/category/{category}")
    public List<Book> getByCategory(@PathVariable Long category) {
        return bookService.getByCategoryId(category);
    }

    //Post чтобы передать в теле запроса дто
    @PostMapping("/get/category/language")
    public List<Book> getByLanguageAndCategory(@RequestBody BookReq request) {
        return bookService.getByLanguageAndCategory(request.getLanguage(), request.getCategory());
    }
}
