package util;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;
public class ValidationUtil {
    public static Object validate(LinkedHashMap<TextField, Pattern> map, Button btn) {
        for (TextField key : map.keySet()) {
            Pattern pattern = map.get(key);
            if (!pattern.matcher(key.getText()).matches()) {
                addError(key, btn);
                return key;
            }
            removeError(key, btn);
        }
        return true;
    }

    private static void removeError(TextField txtField, Button btn) {
        txtField.getParent().setStyle("-fx-border-color: green");

        btn.setDisable(false);
    }

    private static void addError(TextField txtField, Button btn) {
        if (txtField.getText().length() > 0) {
            txtField.getParent().setStyle("-fx-border-color: red");

        }
        btn.setDisable(true);
    }

    public static void textFieldChecker(LinkedHashMap<TextField, Pattern> checkBox, Button btnAddItem, KeyEvent keyEvent) {
        ValidationUtil.validate(checkBox, btnAddItem);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(checkBox, btnAddItem);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            }
        }
    }
}
