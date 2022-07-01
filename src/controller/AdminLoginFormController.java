package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.LoadFXMLFile;

import java.io.IOException;

public class AdminLoginFormController {

    public TextField txtLUName;
    public PasswordField txtLPWord;
    public AnchorPane contextAdmin;

    public void adminLoginOnAction(ActionEvent actionEvent) throws IOException {
        if(txtLUName.getText().equals("Admin") && txtLPWord.getText().equals("1234")){
            LoadFXMLFile.load("UserPrivilegesForm",contextAdmin);
        }
    }
}
