package com.example.cat201librarysystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private static Library instance;
    private List<Book> library;
    private static final String path = "src/main/resources/com/example/cat201librarysystem/CSV_file/books.csv";
    private Library() {
        library = new ArrayList<>();
    }

    public static Library getInstance() throws IOException {
        if (instance == null) {
            instance = new Library();
            instance.loadFromCSV(path);
        }
        return instance;
    }

    public void addBook(Book book) {
        library.add(book);
    }

    public void loadFromCSV(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
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
                addBook(book);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void displayBooks (){
        if (library.isEmpty()){
            System.out.println("No books in library");
            return;
        }
        System.out.println("Books in library:");
        for (Book book : library){
            book.displayDetails();
        }
    }
}
