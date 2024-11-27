package com.example.cat201librarysystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.awt.desktop.SystemEventListener;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class LandingController implements Initializable{
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label incorrectErrorMessage;
    @FXML private Label emptyErrorMessage;
    private Library library;

    public LandingController(Library library) {
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
        incorrectErrorMessage.setVisible(false);
        emptyErrorMessage.setVisible(false);
        System.out.println("Welcome to Landing Page");
    }

    public void handleLogin(ActionEvent e) throws IOException {
        String userID = usernameField.getText();
        String userPw = passwordField.getText();
        boolean isValidAcc = false;

    }

}