package controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.LoadFXMLFile;

import java.io.IOException;

public class MainFormController {
    public AnchorPane context;

    public void openStudentDetail(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("StudentRegistrationForm", context);
    }

    public void openProgramDetails(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("RoomForm", context);
    }
}
