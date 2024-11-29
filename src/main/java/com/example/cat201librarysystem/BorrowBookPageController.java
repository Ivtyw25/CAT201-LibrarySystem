package com.example.cat201librarysystem;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BorrowBookPageController implements Initializable {
    private Library library;

    public BorrowBookPageController(Library library) {
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        library.displayBooks();
    }
}
