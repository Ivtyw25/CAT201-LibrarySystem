package com.example.cat201librarysystem;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private String borrowerName;

    // Constructor
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true; // Books are available by default
        this.borrowerName = null;
    }

    // Convert the book information into CSV format
    public String toCsv() {
        return title + "," + author + "," + isbn;
    }

    // Create a Book object from a CSV record
    public static Book fromCsv(String csvLine) {
        String[] values = csvLine.split(",");
        return new Book(values[0], values[1], values[2]);
    }

    // Getters
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

    // Method to borrow a book
    public boolean borrowBook(String borrowerName) {
        if (isAvailable) {
            this.isAvailable = false;
            this.borrowerName = borrowerName;
            return true;
        }
        return false;
    }

    // Method to return a book
    public void returnBook() {
        this.isAvailable = true;
        this.borrowerName = null;
    }

    // Method to display book details
    public void displayDetails() {
        System.out.println("Book{" +
                "Title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", ISBN='" + isbn + '\'' +
                ", Availability=" + (isAvailable ? "Available" : "Borrowed by " + borrowerName) +
                '}');
    }
}

