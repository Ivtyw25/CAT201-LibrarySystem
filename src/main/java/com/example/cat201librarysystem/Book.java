package com.example.cat201librarysystem;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private String borrowerName;
    private boolean isAvailable;

    public Book(String t, String a, String isbn, String b) {
        this.title = t;
        this.author = a;
        this.isbn = isbn;
        this.borrowerName = b;
        if (borrowerName == null) {
            isAvailable = true;
        }
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getIsbn() {
        return isbn;
    }
    public boolean isAvailable() { return isAvailable; }
    public String getBorrowerName() {
        return borrowerName;
    }

    public StringProperty getTitleProperty() {
        return new SimpleStringProperty(title);
    }
    public StringProperty getAuthorProperty() {
        return new SimpleStringProperty(author);
    }
    public StringProperty getIsbnProperty() {
        return new SimpleStringProperty(isbn);
    }
    public StringProperty getBorrowerNameProperty() { return new SimpleStringProperty(borrowerName); }

    public static Book fromCSV(String line) {
        String[] fields = line.split(",");

        String title = fields[0].trim();
        String author = fields[1].trim();
        String isbn = fields[2].trim();
        String borrowerName = fields[3].trim();

        if (borrowerName.equals("-99")){
            borrowerName = null;
        }
        // Create a new Book object and add it to the library
        Book book = new Book(title, author, isbn, borrowerName);
        return book;
    }

    public boolean borrowBook(String borrowerName) {
        if (borrowerName == null || borrowerName.isEmpty()) {
            return false;
        }
        if (isAvailable) {
            this.isAvailable = false;
            this.borrowerName = borrowerName;
            return true;
        }
        return false;
    }

    public void returnBook() {
        this.isAvailable = true;
        this.borrowerName = null;
    }

    public void displayDetails() {
        System.out.println("Book title: " + title +
                ", author: " + author +
                ", isbn: " + isbn +
                ", borrower: " + borrowerName +
                ", available: " +
                (isAvailable ? "Yes" : "No"
                ));
    }
}
