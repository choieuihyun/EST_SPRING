package com.example.est_spring.day0718_daily_quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Book {

    private long id;
    private String title;
    private String author;
    private String isbn;
    private String price;
    private String publishedYear;

}
