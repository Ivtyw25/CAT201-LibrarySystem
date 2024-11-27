package com.example.cat201librarysystem;

import java.io.*;
import java.util.HashMap;

import static com.example.cat201librarysystem.FileManager.loadFromCSV;

public class Library {

    private static Library instance;
    private HashMap<String, Book> library;
    private static final String path = "src/main/resources/com/example/cat201librarysystem/CSV_file/books.csv";
    private Library() {
        library = new HashMap<>();
    }

    public static Library getInstance() throws IOException {
        if (instance == null) {
            instance = new Library();
            instance.library = loadFromCSV(path);
        }
        return instance;
    }

    public void addBook(String title, String author, String isbn) {
        if (library.containsKey(isbn)) {
            System.out.println("Book already exists");
        } else {
            Book book = new Book(title, author, isbn, "" );
            library.put(isbn, book);
            System.out.println("Book added");
        }
    }

    public HashMap<String, Book> searchByTitle(String title) {
        HashMap<String, Book> results = new HashMap<>();
        for (String isbn : library.keySet()) {
            Book book = library.get(isbn);
            if (book.getTitle().equalsIgnoreCase(title)) {
                results.put(isbn, book);
            }
        }
        return results;
    }

    // Method to search for books by author (results in a HashMap)
    public HashMap<String, Book> searchByAuthor(String author) {
        HashMap<String, Book> results = new HashMap<>();
        for (String isbn : library.keySet()) {
            Book book = library.get(isbn);
            if (book.getAuthor().equalsIgnoreCase(author)) {
                results.put(isbn, book);
            }
        }
        return results;
    }

    // Method to search for a book by ISBN (HashMap allows direct access by key)
    public Book searchByIsbn(String isbn) {
        return library.get(isbn); // No change needed, already optimized for HashMap
    }

    public void displayBooks (){
        if (library.isEmpty()){
            System.out.println("No books in library");
            return;
        }
        System.out.println("Books in library:");
        for (Book book : library.values()){
            book.displayDetails();
        }
    }
}
