package com.example.bookshopmanagementsystem;

import java.sql.Date;

public class customerData {
    private Integer customerId;
    private Integer bookId;
    private String title;
    private String author;
    private String gener; // Updated field name
    private Integer quantity;
    private Double price;
    private Date date;

    public customerData(Integer customerId, Integer bookId, String title, String author, String gener, Integer quantity, Double price, Date date) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.gener = gener; // Updated field name
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGener() { // Updated getter method
        return gener;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}