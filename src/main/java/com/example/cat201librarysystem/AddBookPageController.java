package com.example.cat201librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.ResourceBundle;

public class AddBookPageController implements Initializable {

    @FXML
    private TextField addBookTitle, addBookISBN, addBookAuthor;
    @FXML
    private Button backButton;
    private Library library;

    public AddBookPageController(Library library) {
        this.library = library;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBookTitle.setFocusTraversable(false);
        addBookAuthor.setFocusTraversable(false);
        addBookISBN.setFocusTraversable(false);
        System.out.println("Welcome to Add Book Page");
        library.displayBooks();
    }

    public void onSubmitAddBook(ActionEvent e) {
        String tempTitle = addBookTitle.getText();
        String tempISBN = addBookISBN.getText();
        String tempAuthor = addBookAuthor.getText();

        if (tempTitle.isEmpty() || tempAuthor.isEmpty() || tempISBN.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in all fields");
            alert.showAndWait();
            return;
        }

        if (library.addBook(tempTitle, tempAuthor, tempISBN)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book already exists");
            alert.showAndWait();
        } else {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Book Added Successfully");
            successAlert.showAndWait();
        }
        library.displayBooks();
    }

    public void onClickBack(ActionEvent e) throws IOException {
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
                return null;
            }
        });
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 717, 469));
        stage.show();
    }


}
