package controller;

import bo.BOFactory;
import bo.ReserveBO;
import bo.RoomBO;
import com.jfoenix.controls.JFXButton;
import dto.ReserveDTO;
import dto.RoomDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.LoadFXMLFile;
import util.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class RoomsFormController {
    private final RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ROOM);
    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.RESERVE);
    public AnchorPane contextPro;
    public TextField txtName;
    public TextField txtDuration;
    public TextField txtFee;
    public Label lbID;
    public TableColumn<RoomDTO, String> colID;
    public TableColumn<RoomDTO, String> colName;
    public TableColumn<RoomDTO, String> colDuration;
    public TableColumn<RoomDTO, String> colFee;
    public TableView<RoomDTO> tblProgram;
    public JFXButton btnadd;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    private final LinkedHashMap<TextField, Pattern> roomLHashmap = new LinkedHashMap<>();
    ObservableList<RoomDTO> obList = FXCollections.observableArrayList();
    int rowNumber;

    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("roomQty"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("monthlyRent"));

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        try {
            setRoomID();
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblProgram.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            rowNumber = (Integer) newValue;
            btnadd.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);

            try {
                setRoomData(obList.get(rowNumber).getRoomID());
            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING, "Error").show();
            }
        });

        txtName.setOnAction(event -> {
            validate(KeyCode.ENTER, txtDuration, "^[A-z ]{3,20}$", txtName);
            txtName.setStyle("-jfx-unfocus-color: #89f0c9");
        });

        txtDuration.setOnAction(event -> {
            validate(KeyCode.ENTER, txtFee, "^[1-9 A-z ]{6,8}$", txtDuration);
            txtDuration.setStyle("-jfx-unfocus-color: #89f0c9");
        });
        txtFee.setOnAction(event -> {
            validate(KeyCode.ENTER, txtFee, "^[1-9]{1,}$", txtFee);
            txtFee.setStyle("-jfx-unfocus-color: #89f0c9");
        });

        validation_Detail_Checked_Room();
    }

    public void text_Field_Checker_In_Room(KeyEvent keyEvent) {
        ValidationUtil.textFieldChecker(roomLHashmap, btnadd, keyEvent);
    }

    private void validation_Detail_Checked_Room() {
        roomLHashmap.put(txtName, Pattern.compile("^[A-z]{2,10}$"));
        roomLHashmap.put(txtDuration, Pattern.compile("^[0-9]{1,20}$"));
        roomLHashmap.put(txtFee, Pattern.compile("^([0-9]{5,10})$"));
    }

    public void validate(KeyCode keyCode, TextField txt, String regex, TextField txtNow) {
        if (keyCode == KeyCode.ENTER) {
            if (!txtNow.getText().matches(regex)) {
                txtNow.setText("");
            } else {
                txt.requestFocus();
            }
        }
    }

    private void setRoomData(String programID) throws Exception {
        List<RoomDTO> all = roomBO.findAll();
        for (RoomDTO roomDTO : all) {
            if (roomDTO.getRoomID().equals(programID)) {
//============================================================================================================================================
                txtName.setText(roomDTO.getRoomType());
                txtDuration.setText(String.valueOf(roomDTO.getRoomQty()));//***************************************
                txtFee.setText(String.valueOf(roomDTO.getMonthlyRent()));
                lbID.setText(roomDTO.getRoomID());
//============================================================================================================================================

            }
        }
    }

    public void backToMain(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("MainForm", contextPro);
    }

    public void setRoomID() throws SQLException, ClassNotFoundException {
        lbID.setText(roomBO.generateNewRoomId());
    }

    public void loadTable() throws Exception {
        obList.clear();
        List<RoomDTO> all = roomBO.findAll();

        for (RoomDTO roomDTO : all) {
            obList.add(roomDTO);
        }
        tblProgram.setItems(obList);
    }

    public void addRoom(MouseEvent mouseEvent) {
        if (txtName.getText().trim().isEmpty() || txtDuration.getText().trim().isEmpty() || txtFee.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Empty text Fields").show();
        } else {
            RoomDTO roomDTO = new RoomDTO(
                    lbID.getText(),
                    txtName.getText(),
                    Integer.parseInt(txtDuration.getText()),//********************************************************
                    Double.parseDouble(txtFee.getText()));


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                try {
                    if (roomBO.add(roomDTO)) {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Message");
                        alert2.setContentText("Saved..");
                        alert2.show();

                        setRoomID();
                        loadTable();
                        tblProgram.refresh();
                        clear();

                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.WARNING, e.getClass().getSimpleName()).show();
                }
            }
        }
    }

    public void clear() {
        txtFee.setText("");
        txtDuration.setText("");
        txtName.setText("");
        try {
            setRoomID();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateRoom(MouseEvent mouseEvent) {
        RoomDTO roomDTO = new RoomDTO(
                lbID.getText(),
                txtName.getText(),
                Integer.parseInt(txtDuration.getText()),//**********************************************************
                Double.parseDouble(txtFee.getText()));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                if (roomBO.update(roomDTO)) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Message");
                    alert2.setContentText("Saved..");
                    alert2.show();

                    obList.remove(rowNumber);
                    obList.add(roomDTO);
                    tblProgram.refresh();

                    clear();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteRoom(MouseEvent mouseEvent) {

//        try {
//            List<ReserveDTO> all = reserveBO.findAll();
//            all.removeIf(reserveDTO -> !reserveDTO.getRID().equals(lbID.getText()));
//
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.get() == ButtonType.OK) {
//                boolean bool = false;
//                for (ReserveDTO reserveDTO : all) {
////                    bool = reserveBO.delete(reserveDTO.getId());
//                }
//                if (bool && roomBO.delete(lbID.getText())) {
//
////
//                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
//                    alert2.setTitle("Message");
//                    alert2.setContentText("Saved..");
//                    alert2.show();
//
//                    obList.remove(rowNumber);
//                    tblProgram.refresh();
//
//                } else {
//                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            List<ReserveDTO> all = reserveBO.findAll();
            all.removeIf(reserveDTO -> !reserveDTO.getRID().equals(lbID.getText()));


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                boolean bool = true;
                for (ReserveDTO reserveDTO : all) {
//                    bool = reserveBO.delete(reserveDTO.getId());
                }
                if (bool && roomBO.delete(lbID.getText())) {

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Message");
                    alert2.setContentText("Saved..");
                    alert2.show();

                    obList.remove(rowNumber);
                    tblProgram.refresh();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void deleteRoom(MouseEvent mouseEvent) {
//
//        try {
//            List<ReserveDTO> all = reserveBO.findAll();
//            all.removeIf(reserveDTO -> !reserveDTO.getRID().equals(lbID.getText()));
//
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.get() == ButtonType.OK) {
//                boolean bool = false;
//                for (ReserveDTO reserveDTO : all) {
//                    bool = reserveBO.delete(reserveDTO.getId());
//                }
//                if (bool && roomBO.delete(lbID.getText())) {
//
////
//                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
//                    alert2.setTitle("Message");
//                    alert2.setContentText("Saved..");
//                    alert2.show();
//
//                    obList.remove(rowNumber);
//                    tblProgram.refresh();
//
//                } else {
//                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
