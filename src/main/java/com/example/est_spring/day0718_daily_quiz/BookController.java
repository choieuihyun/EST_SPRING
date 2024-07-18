package com.example.est_spring.day0718_daily_quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 모든 도서 조회
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDtos = bookService.getAllBooks();
        return ResponseEntity.ok().body(bookDtos);
    }

    // 특정 도서 조회
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") long id) {
        BookDTO bookDTO = bookService.getBookById(id);
        return ResponseEntity.ok().body(bookDTO);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO createBookDTO = bookService.createBook(bookDTO);
        return ResponseEntity.ok(createBookDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") long id, @RequestBody BookDTO updateBookDTO) {
        BookDTO bookDTO = bookService.updateBook(id, updateBookDTO);
        return ResponseEntity.ok().body(bookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable("id") long id) {

        if(bookService.deleteBook(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();

    }

}