package com.example.bookshopmanagementsystem;

import java.util.List;

public class Book {
    private String genre;
    private String title;
    private String author;
    private List<String> reviews;

    public Book(String genre, String title, String author, List<String> reviews) {
        this.genre = genre;
        this.title = title;
        this.author = author;
        this.reviews = reviews;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getReviews() {
        return reviews;
    }
}