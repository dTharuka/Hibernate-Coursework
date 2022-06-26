package bo.Impl;

import bo.RoomBO;
import controller.StudentRegFormController;
import dao.DAOFactory;
import dao.custom.RoomDAO;
import dao.custom.impl.RoomDAOImpl;
import dto.RoomDTO;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean add(RoomDTO roomDTO) throws Exception {
        return roomDAO.add(new Room(
                roomDTO.getRoomID(),
                roomDTO.getRoomType(),
                roomDTO.getRoomQty(),//============================================================
                roomDTO.getMonthlyRent()
        ));

    }

    @Override
    public List<RoomDTO> findAll() throws Exception {
        ArrayList<RoomDTO> roomDTOS = new ArrayList<>();
        List<Room> all = roomDAO.findAll();
        for (Room room : all) {
            roomDTOS.add(new RoomDTO(
                    room.getRoomID(),
                    room.getRoomType(),
                    room.getRoomQty(),//============================================================
                    room.getMonthlyRent()
            ));
        }

        return roomDTOS;
    }

    @Override
    public RoomDTO find(String id) throws Exception {
        RoomDAO r1 = new RoomDAOImpl();
        Room r2 = r1.find(id);
        return new RoomDTO(
                r2.getRoomID(),
                r2.getRoomType(),
                r2.getRoomQty(),//============================================================
                r2.getMonthlyRent()
        );


//        return null;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return roomDAO.delete(id);
//        return roomDAO.update(new Room(
//                roomDTO.getRoomID(),
//                roomDTO.getRoomType(),
//                roomDTO.getRoomQty(),
//                roomDTO.getMonthlyRent()
////
//        ));
    }

    @Override
    public boolean update(RoomDTO roomDTO) throws Exception {
        return roomDAO.update(new Room(
                roomDTO.getRoomID(),
                roomDTO.getRoomType(),
                roomDTO.getRoomQty(),//============================================================
                roomDTO.getMonthlyRent()
//
        ));
    }

    @Override
    public String generateNewRoomId() throws SQLException, ClassNotFoundException {
        return roomDAO.generateId();
    }
}
