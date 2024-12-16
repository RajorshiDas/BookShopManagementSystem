package com.example.bookshopmanagementsystem;


import java.sql.Date;

public class bookData {
    private Integer bookId;
    private String title;
    private String author;
    private String gener;
    private Date  date;
    private Double price;
    private String image;

    public bookData(Integer bookId, String title, String author, String gener
            , Date date, Double price, String image){
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.gener = gener;
        this.date = date;
        this.price = price;
        this.image = image;
    }
    public Integer getBookId(){
        return bookId;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getGener(){
        return gener;
    }
    public Date getDate(){
        return date;
    }
    public Double getPrice(){
        return price;
    }
    public String getImage(){
        return image;
    }

}


