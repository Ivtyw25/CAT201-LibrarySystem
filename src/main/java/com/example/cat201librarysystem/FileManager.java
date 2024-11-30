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

    public static boolean checkCredentials(String username, String password, String csvFilePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");

                // Check if the credentials match
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;  // Credentials are correct
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;  // Credentials not found
    }

}
