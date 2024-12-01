package com.example.cat201librarysystem;

import java.io.*;
import java.util.HashMap;

public class FileManager {

    public static HashMap<String, Book> loadFromCSV(String filePath) {
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

    public static void writeToCSV(Library library) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(library.getPath()))) {
            for (Book book : library.getLibrary().values()) {
                String csvLine = String.format("%s,%s,%s,%s",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getIsbn(),
                        book.getBorrowerName() == null || book.getBorrowerName().isEmpty() ? "-99" : book.getBorrowerName()
                );
                writer.write(csvLine);
                writer.newLine();
            }

            System.out.println("Books successfully written to CSV.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    public static boolean checkCredentials(String username, String password, String csvFilePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");

                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
