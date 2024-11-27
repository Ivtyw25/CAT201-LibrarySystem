package com.example.cat201librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private Library library;
    @FXML
    private Button testButton;
    public HomePageController(Library library) {
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
        System.out.println("Welcome to Home Page");
    }

    public void testFunction(ActionEvent e) {
        library.displayBooks();
    }

}
