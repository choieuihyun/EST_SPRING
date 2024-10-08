package com.example.est_spring.day0717_daily_quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookDTO {

    private String title;
    private String author;
    private String isbn;
    private String price;
    private String publishedYear;

}
