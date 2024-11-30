package com.example.cat201librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ReturnBookPageController implements Initializable {
    @FXML private TextField returnBookISBNField;
    @FXML private Button submitReturnBook, backButton;
    private Library library;

    public ReturnBookPageController(Library library) {
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to Return Book Page");
        library.displayBooks();
    }

    public void onSubmitReturnBook(ActionEvent e) {
        String tempISBN = returnBookISBNField.getText();

        if (tempISBN.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in all fields");
            alert.showAndWait();
            return;
        }

        HashMap<String, Book> tempLib = library.getLibrary();
        Book bookToReturn = null;

        for (Map.Entry<String, Book> entry : tempLib.entrySet()) {
            if (entry.getKey().equals(tempISBN)) {
                bookToReturn = entry.getValue();
                break;
            }
        }

        if (bookToReturn == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book Not Found");
            alert.showAndWait();
            return;
        }

        if (bookToReturn.isAvailable()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book Cannot Be Returned");
            alert.setContentText("The book is already available and has not been borrowed.");
            alert.showAndWait();
        } else {
            bookToReturn.returnBook();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Book Returned Successfully");
            successAlert.showAndWait();

            library.displayBooks();
        }
    }

    public void onClickBack(ActionEvent e) throws Exception {
        changeToHomePage();
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
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 717, 469));
        stage.show();
    }
}

