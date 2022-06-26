package bo;

import dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    boolean add(StudentDTO studentDTO) throws Exception;

    List<StudentDTO> findAll() throws Exception;

    StudentDTO find(String id) throws Exception;

    boolean delete(String id) throws Exception;

    boolean update(StudentDTO studentDTO) throws Exception;

    String generateNewStudentId() throws SQLException, ClassNotFoundException;
}
