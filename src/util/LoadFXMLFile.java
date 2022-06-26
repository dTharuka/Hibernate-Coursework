package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoadFXMLFile {
    public static void loadOnTheCurrentPane(String filename, AnchorPane context) throws IOException {
        URL resource = LoadFXMLFile.class.getResource("../view/" + filename + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public static void load(String filename, AnchorPane context) throws IOException {
        URL resource = LoadFXMLFile.class.getResource("../view/" + filename + ".fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) context.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
