package com.example.cat201librarysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;

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

    public HashMap<String, Book> getLibrary() { return library; }

    public boolean addBook(String title, String author, String isbn) {
        if (library.containsKey(isbn)) {
            System.out.println("Book already exists");
            return true;
        } else {
            Book book = new Book(title, author, isbn, null );
            library.put(isbn, book);
            System.out.println("Book added");
            return false;
        }
    }

    public ObservableList<Book> searchBooks(String searchTerm) {
        ObservableList<Book> results = FXCollections.observableArrayList();

        // Make the search term case-insensitive
        String lowerCaseSearchTerm = searchTerm.toLowerCase();

        // Iterate over all books in the library
        for (Book book : library.values()) {
            // Search by title, author, or ISBN (case insensitive)
            if (book.getTitle().toLowerCase().contains(lowerCaseSearchTerm) ||
                    book.getAuthor().toLowerCase().contains(lowerCaseSearchTerm) ||
                    book.getIsbn().toLowerCase().contains(lowerCaseSearchTerm)) {
                results.add(book);
            }
        }

        return results;
    }
    public ObservableList<Book> getAllBooks() {
        return FXCollections.observableArrayList(library.values());
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
