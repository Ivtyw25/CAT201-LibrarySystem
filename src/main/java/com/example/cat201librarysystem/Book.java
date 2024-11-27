package com.example.cat201librarysystem;

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
        if (borrowerName != null) {
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void displayDetails() {
        System.out.println("Book title: " + title + ", author: " + author + ", isbn: " + isbn + ", borrower: " + borrowerName + ", available: " + (isAvailable ? "Yes" : "No"));
    }
}
