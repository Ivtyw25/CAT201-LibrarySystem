package com.example.cat201librarysystem;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class LandingPageController implements Initializable{
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label incorrectErrorMessage, emptyErrorMessage;
    private Library library;
    private static final String adminFilePath = "src/main/resources/com/example/cat201librarysystem/CSV_file/admin.csv";

    public LandingPageController(Library library) {
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
        usernameField.setFocusTraversable(false);
        passwordField.setFocusTraversable(false);
        incorrectErrorMessage.setVisible(false);
        emptyErrorMessage.setVisible(false);
        System.out.println("Welcome to Landing Page");
    }

    public void handleLogin(ActionEvent e) throws IOException {
        String userID = usernameField.getText();
        String userPw = passwordField.getText();
        boolean isValidAcc = FileManager.checkCredentials(userID, userPw, adminFilePath);

        if (isValidAcc) {
            System.out.println("Admin Succesful login");
            changeToHomePage();
        }else if (userID.isEmpty() || userPw.isEmpty()) {
            if (emptyErrorMessage.isVisible())
                emptyErrorMessage.setVisible(false);
            emptyErrorMessage.setVisible(true);
            FadeTransition fadeMessage = new FadeTransition(Duration.millis(4000), emptyErrorMessage);
            fadeMessage.setFromValue(1);
            fadeMessage.setToValue(0);
            fadeMessage.play();
        }else if (!isValidAcc) {
            if (incorrectErrorMessage.isVisible())
                incorrectErrorMessage.setVisible(false);
            incorrectErrorMessage.setVisible(true);
            FadeTransition fadeMessage = new FadeTransition(Duration.millis(4000), incorrectErrorMessage);
            fadeMessage.setFromValue(1);
            fadeMessage.setToValue(0);
            fadeMessage.play();
        }
    }

    public void changeToHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home-page.fxml"));
        fxmlLoader.setControllerFactory(c -> {
            if (c == HomePageController.class) {
                return new HomePageController(library);
            }
            try {
                return c.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 717, 469));
        stage.show();
    }

}