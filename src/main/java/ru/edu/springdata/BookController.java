package ru.edu.springdata;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.edu.springdata.dao.BookDao;
import ru.edu.springdata.model.Book;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAll() {

        List<Book> all = bookDao.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(bookDao.findById(id).get());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Book>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok().body(bookDao.findByName(name));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>> getByCategory(@PathVariable("category") String category) {
        return ResponseEntity.ok().body(bookDao.findByCategory(category));
    }

    @PatchMapping("/update")
    public ResponseEntity<Integer> update(@RequestBody Book book) {
        return ResponseEntity.ok().body(bookDao.update(book));
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody Book book) {
        Long save = bookDao.save(book);
        return ResponseEntity.ok().body(save);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Integer> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(bookDao.deleteById(id));
    }
}
