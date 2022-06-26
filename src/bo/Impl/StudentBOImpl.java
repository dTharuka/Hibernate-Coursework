package bo.Impl;

import bo.StudentBO;
import controller.StudentRegFormController;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Room;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    //============================================================================

//    public void qtyCalc(boolean b) {
//
//        boolean bc = b;
//
//        Room r = new Room();
//
//        if (bc == true) {
//            for (List<Student> list = r.getStudentList().) {
//
//            }
//        }
//
//    }

    //============================================================================

    @Override
    public boolean add(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(new Student(
                studentDTO.getStudentID(),
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getAddress(),
                studentDTO.getContactNo(),
                studentDTO.getDateOfBirth(),
                studentDTO.getNic(),
                studentDTO.getGender(),
                studentDTO.getKeyMoney()

        ));
    }

    @Override
    public List<StudentDTO> findAll() throws Exception {
//        return null;
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> all = studentDAO.findAll();
        for (Student student : all) {
            studentDTOS.add(new StudentDTO(
                    student.getStudentID(),
                    student.getName(),
                    student.getAge(),
                    student.getAddress(),
                    student.getContactNo(),
                    student.getDateOfBirth(),
                    student.getNic(),
                    student.getGender(),
                    student.getKeyMoney()
            ));
        }

        return studentDTOS;
    }

    @Override
    public StudentDTO find(String id) throws Exception {
        Student student = studentDAO.find(id);
        return new StudentDTO(
                student.getStudentID(),
                student.getName(),
                student.getAge(),
                student.getAddress(),
                student.getContactNo(),
                student.getDateOfBirth(),
                student.getNic(),
                student.getGender(),
                student.getKeyMoney()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override//=================================================================
    public boolean update(StudentDTO customerDTO) throws Exception {
        return studentDAO.update(new Student(
                customerDTO.getStudentID(),
                customerDTO.getName(),
                customerDTO.getAge(),
                customerDTO.getAddress(),
                customerDTO.getContactNo(),
                customerDTO.getDateOfBirth(),
                customerDTO.getNic(),
                customerDTO.getGender(),
                customerDTO.getKeyMoney()
        ));
    }

    @Override
    public String generateNewStudentId() throws SQLException, ClassNotFoundException {
        return studentDAO.generateId();
    }
}
