package com.example.cat201librarysystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILE_NAME = "books.csv";

    // Method to load books from CSV file
    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return books; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(Book.fromCsv(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Method to save books to CSV file
    public static void saveBooks(List<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                writer.write(book.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to display all books
    public static void displayBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Method to add a new book
    public static void addBook(List<Book> books, String title, String author, String isbn) {
        books.add(new Book(title, author, isbn));
        saveBooks(books); // Save the updated list to the file
    }
}

