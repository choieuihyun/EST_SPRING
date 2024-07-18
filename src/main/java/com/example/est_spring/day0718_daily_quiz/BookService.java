package com.example.est_spring.day0718_daily_quiz;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    List<Book> books = new ArrayList<>();
    private Long bookId = 1L;

    List<BookDTO> getAllBooks() {
        return books.stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    BookDTO getBookById(Long id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .map(this::convertToBookDTO)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없어요"));
    }

    BookDTO createBook(BookDTO bookDTO) {
        Book book = convertToBookEntity(bookDTO);
        book.setId(bookId++);
        books.add(book);
        return convertToBookDTO(book);
    }

    BookDTO updateBook(Long id, BookDTO bookDTO) {

        // 객체 주소가 유지되어 리스트에 추가하지 않아도 된다.
        Book book = findBookById(id);
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublishedYear(bookDTO.getPublishedYear());

        return convertToBookDTO(book);
    }

    boolean deleteBook(Long id) {
        try {
            Book book = findBookById(id);
            books.remove(book);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private Book convertToBookEntity(BookDTO bookDTO) {
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
        bookDTO.setPublishedYear(book.getPublishedYear());

        return bookDTO;
    }

    private Book findBookById (Long id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 삭제."));
    }


}
