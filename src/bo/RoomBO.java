package bo;

import dto.RoomDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    boolean add(RoomDTO roomDTO) throws Exception;

    List<RoomDTO> findAll() throws Exception;

    RoomDTO find(String id) throws Exception;

    boolean delete(String id) throws Exception;

    boolean update(RoomDTO roomDTO) throws Exception;

    String generateNewRoomId() throws SQLException, ClassNotFoundException;


}
