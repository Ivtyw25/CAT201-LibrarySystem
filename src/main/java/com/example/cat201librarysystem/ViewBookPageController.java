package com.example.cat201librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book is already borrowed by someone else or borrower name is empty");
            alert.showAndWait();
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
        library.removeBook(book);

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText("Book Deleted Successfully");
        successAlert.setContentText("The book has been removed from the library.");
        successAlert.showAndWait();

        library.displayBooks();
        changeToSearchPage();

    }

    public void onClickEdit(ActionEvent e) throws Exception {
        TextInputDialog titleDialog = new TextInputDialog(book.getTitle());
        titleDialog.setTitle("Edit Book Information");
        titleDialog.setHeaderText("Edit the title of the book:");
        titleDialog.setContentText("New Title:");

        Optional<String> newTitle = titleDialog.showAndWait();
        if (newTitle.isPresent() && newTitle.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Title cannot be empty");
            alert.showAndWait();
            return;
        }

        TextInputDialog authorDialog = new TextInputDialog(book.getAuthor());
        authorDialog.setTitle("Edit Book Information");
        authorDialog.setHeaderText("Edit the author of the book:");
        authorDialog.setContentText("New Author:");

        Optional<String> newAuthor = authorDialog.showAndWait();
        if (newAuthor.isPresent() && newAuthor.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Author cannot be empty");
            alert.showAndWait();
            return;
        }

        TextInputDialog isbnDialog = new TextInputDialog(book.getIsbn());
        isbnDialog.setTitle("Edit Book Information");
        isbnDialog.setHeaderText("Edit the ISBN of the book:");
        isbnDialog.setContentText("New ISBN:");

        Optional<String> newIsbn = isbnDialog.showAndWait();
        if (newIsbn.isPresent() && newIsbn.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("ISBN cannot be empty");
            alert.showAndWait();
            return;
        }

        if (newTitle.isPresent() && newAuthor.isPresent() && newIsbn.isPresent()) {
            book.setTitle(newTitle.get());
            book.setAuthor(newAuthor.get());
            book.setIsbn(newIsbn.get());

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Book Updated Successfully");
            successAlert.setContentText("Book details have been updated.");
            successAlert.showAndWait();

            library.displayBooks();
            changeToSearchPage();
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
