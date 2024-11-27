module com.example.cat201librarysystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires annotations;

    opens com.example.cat201librarysystem to javafx.fxml;
    exports com.example.cat201librarysystem;
}