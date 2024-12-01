package com.example.cat201librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class ViewBookPageController implements Initializable {
    @FXML
    private Label titleLabel, authorLabel, ISBNLabel, borrowerNameLabel;
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
        borrowerNameLabel.setText(book.getBorrowerName());
    }

    public void errorMessageWindow(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void infoMessageWindow(String Title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Optional<String> showInputDialog(String currentValue, String title, String header, String content) {
        TextInputDialog dialog = new TextInputDialog(currentValue);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        return dialog.showAndWait();
    }

    private boolean isInputValid(String input, String errorMessage) {
        // Check if the input is empty and show an error if so
        if (input.isEmpty()) {
            errorMessageWindow("Invalid Input", errorMessage);
            return false;
        }
        return true;
    }


    public void onClickBack(ActionEvent e) throws Exception {
        changeToSearchPage();
    }

    public void onClickReturn(ActionEvent e) throws Exception {
        if (book.isAvailable()) {
            errorMessageWindow("Book Cannot Be Returned", "The book is already available and has not been borrowed.");
        } else {
            book.returnBook();
            infoMessageWindow("Success", "Book Returned Successfully", "");
            changeToSearchPage();
            library.displayBooks();
        }
    }

    public void onClickBorrow(ActionEvent e) throws Exception {
        AtomicReference<String> borrowerName = new AtomicReference<>();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Borrower Information");
        dialog.setHeaderText("Please enter the borrower's name:");
        dialog.setContentText("Borrower's Name:");

        dialog.showAndWait().ifPresent(borrowerName::set);

        if(!book.borrowBook(borrowerName.get())) {
            errorMessageWindow("Book is already borrowed by someone else or borrower name is empty", "");
        } else {
            System.out.println("Book borrowed successfully");
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Book Borrowed Successfully");
            successAlert.setContentText("book: " + book.getTitle() + " has successfully been borrowed by " + borrowerName.get());
            successAlert.showAndWait();
            changeToSearchPage();
            library.displayBooks();
        }
    }

    public void onClickDelete(ActionEvent e) throws Exception {
        if (library.removeBook(book)) {
            infoMessageWindow("Success", "Book Deleted Successfully", "The book has been removed from the library.");
            library.displayBooks();
            changeToSearchPage();
        } else {
            errorMessageWindow("Error", "The book delete unsuccessful.");
        }

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
