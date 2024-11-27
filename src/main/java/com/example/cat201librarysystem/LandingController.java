package com.example.cat201librarysystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.awt.desktop.SystemEventListener;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class LandingController implements Initializable{
    @FXML private TextField idField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    private Library library;

    public LandingController(Library library) {
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
    }

    public void handleLogin(ActionEvent e) throws IOException {
        System.out.print("hello");
        library.displayBooks();
    }

}