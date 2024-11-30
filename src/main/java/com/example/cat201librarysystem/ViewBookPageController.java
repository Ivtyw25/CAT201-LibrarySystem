package com.example.cat201librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewBookPageController implements Initializable {
    @FXML
    private Label titleLabel, authorLabel, ISBNLabel, availabilityLabel, borrowerNameLabel;
    @FXML
    private Button backButton, returnButton, borrowButton;
    private Book book;
    private Library library;
    public ViewBookPageController(Book book, Library library) {
        this.book = book;
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to View Book Page");
        library.displayBooks();

        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());
        ISBNLabel.setText(book.getIsbn());
        availabilityLabel.setText(book.isAvailable() ? "Available" : "Not Available");
        borrowerNameLabel.setText(book.getBorrowerName());
    }

    public void onClickBack(ActionEvent e) throws Exception {
        changeToSearchPage();
    }

    public void onClickReturn(ActionEvent e) throws Exception {
        if (book.isAvailable()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book Cannot Be Returned");
            alert.setContentText("The book is already available and has not been borrowed.");
            alert.showAndWait();
        } else {
            book.returnBook();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Book Returned Successfully");
            successAlert.showAndWait();
        }
    }

    public void onClickBorrow(ActionEvent e) throws Exception {

    }

    public void changeToSearchPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchbook-page.fxml"));
        fxmlLoader.setControllerFactory(c -> {
            if (c == SearchBookPageController.class) {
                return new SearchBookPageController(library);
            }
            try {
                return c.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 717, 469));
        stage.show();
    }
}
