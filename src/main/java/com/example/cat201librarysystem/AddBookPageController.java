package com.example.cat201librarysystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookPageController implements Initializable {
    private Library library;
    public AddBookPageController(Library library) {
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        library.displayBooks();
    }
}
