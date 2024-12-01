package com.example.cat201librarysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.cat201librarysystem.FileManager.writeToCSV;

public class LibrarySystem extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LibrarySystem.class.getResource("landing-page.fxml"));
        Library library = Library.getInstance();
        fxmlLoader.setControllerFactory(c -> {
            if (c == LandingPageController.class) {
                return new LandingPageController(library); // Pass the library to the controller
            }
            try {
                return c.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });

        Scene scene = new Scene(fxmlLoader.load(), 717, 469);
        stage.setTitle("Library Management System");
        stage.setOnCloseRequest(e -> {
            writeToCSV(library);
        });

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}