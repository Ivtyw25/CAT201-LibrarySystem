package com.example.cat201librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private Library library;
    @FXML
    private Button toAddBookPageButton;
    @FXML
    private Button toBorrowBookPageButton;
    public HomePageController(Library library) {
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
        System.out.println("Welcome to Home Page");
    }

    public void onClickAddBook (ActionEvent e) throws IOException {
        changeToAddBookPage();
    }

    public void onClickBorrowBook (ActionEvent e) throws IOException {
        changeToBorrowBookPage();
    }

    public void changeToAddBookPage () throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addbook-page.fxml"));
        fxmlLoader.setControllerFactory(c -> {
            if (c == AddBookPageController.class) {
                return new AddBookPageController(library);
            }
            try {
                return c.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
        Stage stage = (Stage) toAddBookPageButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 717, 469));
        stage.show();
    }

    public void changeToBorrowBookPage () throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("borrowbook-page.fxml"));
        fxmlLoader.setControllerFactory(c -> {
            if (c == BorrowBookPageController.class) {
                return new BorrowBookPageController(library);
            }
            try {
                return c.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
        Stage stage = (Stage) toBorrowBookPageButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 717, 469));
        stage.show();
    }

}

