package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.LoadFXMLFile;

import java.io.IOException;

public class MainFormController {
    public AnchorPane context;
    public JFXButton studentBtn;

    public void openStudentDetail(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("StudentRegistrationForm", context);
    }

    public void openProgramDetails(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("RoomForm", context);
    }

    public void userOnAction(ActionEvent actionEvent) throws IOException {
        LoadFXMLFile.load("AdminLoginForm", context);
    }

    public void ReserveOnAction(ActionEvent actionEvent) throws IOException {
        LoadFXMLFile.load("ReserveViewForm", context);
    }
}
