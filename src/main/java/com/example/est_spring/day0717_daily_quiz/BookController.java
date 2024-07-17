package com.example.est_spring.day0717_daily_quiz;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private long nextId = 1;

    private static Book convertToBookEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setPrice(bookDTO.getPrice());
        book.setPublishedYear(bookDTO.getPublishedYear());

        return book;
    }

    private BookDTO convertToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPrice(book.getPrice());
        book.setPublishedYear(book.getPublishedYear());

        return bookDTO;
    }

    // 모든 도서 조회
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return books.stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    // 특정 도서 조회
    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable("id") long id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .map(this::convertToBookDTO)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없어요."));

    }

    @PostMapping
    public Book addBook(@RequestBody BookDTO bookDTO) {
        Book book = convertToBookEntity(bookDTO);
        book.setId(nextId++);
        books.add(book);

        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") long id, @RequestBody BookDTO updateBookDTO) {

        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .map(b -> {
                    b.setTitle(updateBookDTO.getTitle());
                    b.setAuthor(updateBookDTO.getAuthor());
                    b.setIsbn(updateBookDTO.getIsbn());
                    b.setPrice(updateBookDTO.getPrice());
                    b.setPublishedYear(updateBookDTO.getPublishedYear());
                    return b;
                })
                .orElseThrow(() -> new IllegalArgumentException("책을 못찾았어요."));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        books.removeIf(b -> b.getId() == id);
    }

}