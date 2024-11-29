package com.example.cat201librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class BorrowBookPageController implements Initializable {
    @FXML
    private TextField borrowBookISBN, borrowBookBorrowerName;
    @FXML
    private Button submitBorrowBook;
    private Library library;

    public BorrowBookPageController(Library library) {
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to Borrow Book Page");
        library.displayBooks();
    }

    public void onSubmitBorrowBook(ActionEvent e) {
        String tempISBN = borrowBookISBN.getText();
        String tempBorrowerName = borrowBookBorrowerName.getText();

        HashMap<String, Book> tempLib = library.getLibrary();
        Book bookToBorrow = null;

        for (Map.Entry<String, Book> entry : tempLib.entrySet()) {
            if (entry.getKey().equals(tempISBN)) {
                bookToBorrow = entry.getValue();
                break;
            }
        }

        if (bookToBorrow == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book does not exist");
            alert.showAndWait();
            return;
        }

        if(!bookToBorrow.borrowBook(tempBorrowerName)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book is already borrowed by someone else");
            alert.showAndWait();
        } else {
            System.out.println("Book borrowed successfully");
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Book Borrowed Successfully");
            successAlert.setContentText("You have successfully borrowed the book: " + bookToBorrow.getTitle());
            successAlert.showAndWait();
            library.displayBooks();
        }
    }


}
