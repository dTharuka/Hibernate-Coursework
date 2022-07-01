package controller;

import bo.BOFactory;
import bo.Impl.RoomBOImpl;
import bo.Impl.StudentBOImpl;
import bo.ReserveBO;
import bo.RoomBO;
import bo.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import dao.custom.RoomDAO;
import dao.custom.impl.RoomDAOImpl;
import dto.ReserveDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class StudentRegFormController {
    public static int temp01 = 0;//==========================================================
    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);
    private final RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ROOM);
    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.RESERVE);
    public AnchorPane contextStd;
    public TextField txtsName;
    public TextField txtAge;
    public TextField txtPhone;
    public TextField txtNIC;
//    public TextArea txtAddress;
    public TextField key_money;
    public Label lbID;
    public JFXDatePicker dob;
    public JFXComboBox<String> cmpProgram;
    public Label lbRegDate;
    public Label lbsID;
    public TextField txtID;
    public JFXComboBox<String> cmbRegProgram;
    public TextField txtRegDate;
    public Label txtFee;
    public AnchorPane btnClear;
    public JFXButton btnDelete;
    public TextField genderTxt;

    public TableView<StudentDTO> studentTBL;
    public TableColumn<StudentDTO, String> stuIdCol;
    public TableColumn<StudentDTO, String> AddressCol;
    public TableColumn<StudentDTO, String> dobCol;
    public TableColumn<StudentDTO, String> GenderCol;
    public TableColumn<StudentDTO, String> NameCol;
    public TableColumn<StudentDTO, String> NicCol;
    public TableColumn<StudentDTO, String> k_moneyCol;
    public TableColumn<StudentDTO, String> ageCol;
    public TableColumn<StudentDTO, String> contactCol;
    public JFXButton regID;
    public JFXButton btnUpdate;
    public JFXButton clearBtn;
    public TextField txtRQty;
    private final LinkedHashMap<TextField, Pattern> studentLHashmap = new LinkedHashMap<>();
    public TextField txtAddress;
    int stRowNumber;
    ObservableList<StudentDTO> stObList = FXCollections.observableArrayList();

    public void initialize() {
        stuIdCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        NicCol.setCellValueFactory(new PropertyValueFactory<>("nic"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        k_moneyCol.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));


        try {
            setStudentID();//==============
            loadStudentTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //============================================================

        studentTBL.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            stRowNumber = (Integer) newValue;
            regID.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            clearBtn.setDisable(false);

            try {
                setStudentData(stObList.get(stRowNumber).getStudentID());
            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING, "Error").show();
            }
        });


        //============================================================

        loadDate();

        btnDelete.setDisable(true);

        try {
            setStudentID();
            loadStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbRegProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //========================================================================================
//            StudentBO studentBO = new StudentBOImpl();
//            RoomBO roomBO = new RoomBOImpl();
            try {
                RoomDTO roomDTO = roomBO.find(newValue);

                StudentDTO studentDTO = studentBO.find(oldValue);
                txtFee.setText(String.valueOf(roomDTO.getMonthlyRent()-studentDTO.getKeyMoney()));

            } catch (Exception e) {
                e.printStackTrace();
            }
//==========================================================================================================================

            try {
                setRegDate(String.valueOf(newValue));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        cmpProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            //DI
//            RoomBO roomBO = new RoomBOImpl();
            try {
                RoomDTO roomDTO = roomBO.find(newValue);
                txtRQty.setText(String.valueOf(roomDTO.getRoomQty()));

//                txtRQty.clear();
//                roomDTO.setRoomQty(roomDTO.getRoomQty()-1);

//                txtRQty.setText(String.valueOf(roomDTO.getRoomQty()-1));


            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                setFee(String.valueOf(newValue));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        txtsName.setOnAction(event -> {
            validate(KeyCode.ENTER, txtAge, "^[A-z ]{3,20}$", txtsName);
            txtsName.setStyle("-jfx-unfocus-color: #89f0c9");
        });

        txtAge.setOnAction(event -> {
            validate(KeyCode.ENTER, txtPhone, "^[1-9]{1,2}$", txtAge);
            txtAge.setStyle("-jfx-unfocus-color: #89f0c9");
        });

        txtPhone.setOnAction(event -> {
            validate(KeyCode.ENTER, txtNIC, "^[0-9]{10}$", txtPhone);
            txtPhone.setStyle("-jfx-unfocus-color: #89f0c9");
        });
        validation_Detail_Checked_Student();
    }


    public void text_Field_Checker_In_Student(KeyEvent keyEvent) {
        ValidationUtil.textFieldChecker(studentLHashmap, regID, keyEvent);
    }

    private void validation_Detail_Checked_Student() {
        studentLHashmap.put(txtsName, Pattern.compile("^[A-z ]{3,20}$"));
        studentLHashmap.put(txtAge, Pattern.compile("^[0-9]{1,2}$"));
        studentLHashmap.put(txtPhone, Pattern.compile("^([0-9]{10})$"));//
        studentLHashmap.put(txtNIC, Pattern.compile("^[0-9]{12}$"));//
        studentLHashmap.put(txtAddress, Pattern.compile("^[A-z ]{5,20}$"));
        studentLHashmap.put(genderTxt, Pattern.compile("^((male)|(female))$"));
        studentLHashmap.put(key_money, Pattern.compile("^[0-9]{3,10}$"));
    }

    //================================================================

    private void setStudentData(String programID) throws Exception {

        List<RoomDTO> roomDTOList = roomBO.findAll();
        List<StudentDTO> all = studentBO.findAll();
        for (StudentDTO studentDTO : all) {
            if (studentDTO.getStudentID().equals(programID)) {

                txtID.setText(studentDTO.getStudentID());

                //--------------------------------------------------------------------------------

                txtsName.setText(studentDTO.getName());
                txtAge.setText(String.valueOf(studentDTO.getAge()));
                txtAddress.setText(studentDTO.getAddress());
                txtPhone.setText(studentDTO.getContactNo());
                dob.setValue(LocalDate.parse(studentDTO.getDateOfBirth()));
                txtNIC.setText(studentDTO.getNic());
                genderTxt.setText(studentDTO.getGender());
                key_money.setText(String.valueOf(studentDTO.getKeyMoney()));
                lbsID.setText(studentDTO.getStudentID());


            }


        }
    }


    public void loadStudentTable() throws Exception {
        stObList.clear();
        List<StudentDTO> all = studentBO.findAll();

        for (StudentDTO studentDTO : all) {
            stObList.add(studentDTO);
        }
        studentTBL.setItems(stObList);
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

    private void setFee(String valueOf) throws Exception {
        List<RoomDTO> all = roomBO.findAll();
        for (RoomDTO roomDTO : all) {
            if (roomDTO.getRoomID().equals(cmpProgram.getSelectionModel().getSelectedItem())) {
//                txtFee.setText(String.valueOf(roomDTO.getMonthlyRent()));
                txtFee.setText(String.valueOf(roomDTO.getMonthlyRent()-Integer.parseInt(key_money.getText())));
                return;
            }
        }
    }

    private void setRegDate(String newValue) throws Exception {
        List<ReserveDTO> all = reserveBO.findAll();//======================
        for (ReserveDTO reserveDTO : all) {
            if (reserveDTO.getSID().equals(lbsID.getText()) && reserveDTO.getRID().equals(cmbRegProgram.getSelectionModel().getSelectedItem())) {
                txtRegDate.setText(reserveDTO.getDate());
                return;
            }
        }
    }

    public void loadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lbRegDate.setText(f.format(date));
    }

    public void goToMain(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("MainForm", contextStd);
    }

    public void loadStudents() throws Exception {
        List<RoomDTO> roomDTOList = roomBO.findAll();

        List<String> idList = new ArrayList<>();


        for (RoomDTO roomDTO : roomDTOList) {
            idList.add(roomDTO.getRoomID());
        }

        cmpProgram.getItems().addAll(idList);

    }

    public void setStudentID() throws SQLException, ClassNotFoundException {
        lbsID.setText(studentBO.generateNewStudentId());
    }

    public void register(MouseEvent mouseEvent) throws Exception {
        if (txtsName.getText().trim().isEmpty() || txtAge.getText().trim().isEmpty() || txtNIC.getText().trim().isEmpty() || txtAddress.getText().trim().isEmpty() || txtPhone.getText().trim().isEmpty() || dob.getValue() == null || cmpProgram.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Empty").show();
        } else {


            if (txtID.getText().trim().isEmpty() || !isExists(txtID.getText())) {

                StudentDTO student = new StudentDTO(
                        lbsID.getText(),
                        txtsName.getText(),
                        Integer.parseInt(txtAge.getText()),
                        txtAddress.getText(),
                        txtPhone.getText(),
                        dob.getValue().toString(),
                        txtNIC.getText(),
                        genderTxt.getText(),

                        Double.parseDouble(key_money.getText())

                        //==========================================================================================
                );

                ReserveDTO reserveDTO = null;
                try {
                    reserveDTO = new ReserveDTO(
                            reserveBO.generateNewRoomId(),
                            lbsID.getText(),
                            cmpProgram.getSelectionModel().getSelectedItem(),
                            lbRegDate.getText(),
//                            Double.parseDouble(key_money.getText()
                            Double.parseDouble(txtFee.getText()

                    ));
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
//                loadStudentTable();///====================

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    try {
                        if (studentBO.add(student) && reserveBO.add(reserveDTO)) {
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Message");
                            alert2.setContentText("Saved..");
                            alert2.show();

                            loadStudentTable();


                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                        }
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                    }
                }

            } else {
                ReserveDTO reserveDTO = null;
                try {
                    reserveDTO = new ReserveDTO(
                            reserveBO.generateNewRoomId(),
                            lbsID.getText(),
                            cmpProgram.getSelectionModel().getSelectedItem(),
                            lbRegDate.getText(),
                            Double.parseDouble(key_money.getText()
                                    //===============================================================================================================

                            ));
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    try {
                        if (reserveBO.add(reserveDTO)) {
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Message");
                            alert2.setContentText("Saved..");
                            alert2.show();
                            loadStudentTable();//===============================
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                        }
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                    }
                }
            }
        }
    }

    public boolean isExists(String id) {
        StudentDTO studentDTO = null;
        try {
            studentDTO = studentBO.find(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentDTO != null;
    }

    public void deleteStudent(MouseEvent mouseEvent) {

        try {
            List<ReserveDTO> all = reserveBO.findAll();//==============================================
            all.removeIf(reserveDTO -> !reserveDTO.getSID().equals(lbsID.getText()));


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                boolean bool = false;
                for (ReserveDTO reserveDTO : all) {
                    bool = reserveBO.delete(reserveDTO.getId());//----------------
//                    RoomBO roomBO = new RoomBOImpl();
                    RoomDTO roomDTO = roomBO.find(reserveDTO.getRID());
                    roomDTO.setRoomQty(roomDTO.getRoomQty()+1);
                    roomBO.update(roomDTO);
                }
                if (bool && studentBO.delete(lbsID.getText())) {

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Message");
                    alert2.setContentText("Saved..");
                    alert2.show();
//                    RoomsFormController r1=new RoomsFormController();
//                    txtRQty.setText(String.valueOf(Integer.parseInt(r1.colDuration.getText())+1));
                    loadStudentTable();

//                } else {
                }
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        } catch (Exception ignored) {
        }
    }

    public void UpdateStudent(MouseEvent mouseEvent) {
        StudentDTO studentDTO = new StudentDTO(
                txtID.getText(),
                txtsName.getText(), Integer.parseInt(txtAge.getText()),
                txtAddress.getText(), txtPhone.getText(),
                String.valueOf(dob.getValue()), txtNIC.getText(),
                genderTxt.getText(), Double.parseDouble(key_money.getText())
        );

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();


        if (result.get() == ButtonType.OK) {
            try {
                if (studentBO.update(studentDTO)) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Message");
                    alert2.setContentText("Saved..");
                    alert2.show();


                    stObList.remove(stRowNumber);
                    stObList.add(studentDTO);
                    studentTBL.refresh();

                    clear();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void clear() {
        btnDelete.setDisable(true);
        txtFee.setText("");
        txtRegDate.clear();
        txtPhone.clear();
        txtAddress.clear();
        txtNIC.clear();
        txtAge.clear();
        txtID.clear();
        txtsName.clear();
        key_money.clear();
        genderTxt.clear();
        dob.setValue(null);
        cmbRegProgram.getItems().clear();
        try {
            setStudentID();
        } catch (SQLException | ClassNotFoundException ignored) {
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        if (txtID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Empty").show();
        } else {
            try {
                btnDelete.setDisable(false);
                StudentDTO studentDTO = studentBO.find(txtID.getText());
                List<ReserveDTO> reserveDTOS = reserveBO.findAll();//==========================
                if (studentDTO != null) {
                    txtsName.setText(studentDTO.getName());
                    txtAge.setText(String.valueOf(studentDTO.getAge()));
                    txtAddress.setText(studentDTO.getAddress());
                    txtPhone.setText(studentDTO.getContactNo());
                    dob.setValue(LocalDate.parse(studentDTO.getDateOfBirth()));
                    txtNIC.setText(studentDTO.getNic());
                    genderTxt.setText(studentDTO.getGender());
                    key_money.setText(Double.toString(studentDTO.getKeyMoney()));
                    lbsID.setText(studentDTO.getStudentID());

                    //=======================================================================

                    List<RoomDTO> all = roomBO.findAll();

                    for (RoomDTO roomDTO : all) {
                        if (roomDTO.getMonthlyRent() > studentDTO.getKeyMoney()) {
                            txtFee.setText(String.valueOf((roomDTO.getMonthlyRent() - Double.parseDouble(key_money.getText()))));
                            System.out.println(txtFee.getText());

                        }
                    }



                    List<String> ids = new ArrayList<>();
                    for (ReserveDTO reserveDTO : reserveDTOS) {
                        if (reserveDTO.getSID().equals(txtID.getText())) {
                            ids.add(reserveDTO.getRID());
                        }
                    }

                    cmbRegProgram.getItems().addAll(ids);

                } else {
                    new Alert(Alert.AlertType.WARNING, "Data not found").show();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

}
