package com.example.cat201librarysystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;

import java.util.ResourceBundle;

public class LandingController implements Initializable{
    @FXML private TextField idField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
        System.out.print("hell");
    }

}