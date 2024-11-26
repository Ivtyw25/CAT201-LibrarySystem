package com.example.cat201librarysystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library {
    private HashMap<String, Book> books; // HashMap to store books with ISBN as the key

    // Constructor
    public Library() {
        books = new HashMap<>();
    }

    // Method to add a book
    public void addBook(String title, String author, String isbn) {
        if (books.containsKey(isbn)) {
            System.out.println("A book with this ISBN already exists: " + books.get(isbn));
        } else {
            Book newBook = new Book(title, author, isbn);
            books.put(isbn, newBook);
            System.out.println("Book added successfully: " + title);
        }
    }

    // Method to search for books by title
    public List<Book> searchByTitle(String title) {
        List<Book> results = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                results.add(book);
            }
        }
        return results;
    }

    // Method to search for books by author
    public List<Book> searchByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                results.add(book);
            }
        }
        return results;
    }

    // Method to search for a book by ISBN
    public Book searchByIsbn(String isbn) {
        return books.get(isbn);
    }

    // Main method for testing
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        library.addBook("The Great Gatsby", "F. Scott Fitzgerald", "1234567890");
        library.addBook("1984", "George Orwell", "2345678901");
        library.addBook("To Kill a Mockingbird", "Harper Lee", "3456789012");

        // Searching by title
        System.out.println("\nSearch by title '1984':");
        List<Book> titleSearchResults = library.searchByTitle("1984");
        titleSearchResults.forEach(Book::displayDetails);

        // Borrowing a book
        System.out.println("\nBorrowing '1984':");
        Book bookToBorrow = library.searchByIsbn("2345678901");
        if (bookToBorrow != null && bookToBorrow.borrowBook("Alice")) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Failed to borrow the book.");
        }

        // Attempting to borrow the same book again
        System.out.println("\nBorrowing '1984' again:");
        if (bookToBorrow != null && bookToBorrow.borrowBook("Bob")) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Failed to borrow the book.");
        }

        // Returning a book
        System.out.println("\nReturning '1984':");
        if (bookToBorrow != null) {
            bookToBorrow.returnBook();
            System.out.println("Book returned successfully.");
        }

        // Displaying details of all books
        System.out.println("\nAll books in the library:");
        for (Book book : library.books.values()) {
            book.displayDetails();
        }
    }
}

