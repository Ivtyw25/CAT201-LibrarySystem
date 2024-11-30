package com.example.cat201librarysystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchBookPageController implements Initializable{
    @FXML
    private Button backButton;
    @FXML
    private TableView<Book> bookTableView;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> ISBNColumn;
    @FXML
    private TableColumn<Book, String> borrowerNameColumn;
    @FXML
    private TextField searchField;
    private Library library;

    public SearchBookPageController(Library library) {
        this.library = library;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to Search Book Page");
        library.displayBooks();
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthorProperty());
        ISBNColumn.setCellValueFactory(cellData -> cellData.getValue().getIsbnProperty());
        borrowerNameColumn.setCellValueFactory(cellData -> cellData.getValue().getBorrowerNameProperty());

        bookTableView.setItems(library.getAllBooks());
        searchField.textProperty().addListener((observable, oldValue, newValue) -> performSearch(newValue));

        bookTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Book selectedbook = bookTableView.getSelectionModel().getSelectedItem();
                if (selectedbook != null) {
                    try {
                        changeToViewBookPage(selectedbook);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void performSearch(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            // If search bar is empty, show all books
            bookTableView.setItems(library.getAllBooks());
        } else {
            // Filter books by title (or extend to author/ISBN if needed)
            ObservableList<Book> results = library.searchBooks(searchTerm);
            bookTableView.setItems(results);
        }
    }

    public void changeToViewBookPage(Book book) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewbook-page.fxml"));
        fxmlLoader.setControllerFactory(c -> {
            if (c == ViewBookPageController.class) {
                return new ViewBookPageController(book, library);
            }
            try {
                return c.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
        Stage stage = (Stage) bookTableView.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 717, 469));
        stage.show();
    }


}
