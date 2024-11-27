package com.example.cat201librarysystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileManager {

    public static HashMap<String, Book> loadFromCSV(String filePath) throws IOException {
        HashMap<String, Book> library = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                Book book = Book.fromCSV(line);
                library.put(book.getIsbn(), book);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return library;
    }
}
