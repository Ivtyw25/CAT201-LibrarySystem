package com.example.cat201librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.util.ResourceBundle;

public class AddBookPageController implements Initializable {

    @FXML private TextField addBookTitle, addBookISBN, addBookAuthor;
    @FXML private Button submitAddBook;

    private Library library;
    public AddBookPageController(Library library) {
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to Add Book Page");
        library.displayBooks();
    }

    public void onSubmitAddBook(ActionEvent e) {
        String tempTitle = addBookTitle.getText();
        String tempISBN = addBookISBN.getText();
        String tempAuthor = addBookAuthor.getText();

        if (library.addBook(tempTitle, tempAuthor, tempISBN)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book already exists");
            alert.showAndWait();
        }
        library.displayBooks();
    }


}
