package bo.Impl;

import bo.ReserveBO;
import controller.StudentRegFormController;
import dao.DAOFactory;
import dao.custom.RoomDAO;
import dao.custom.ReserveDAO;
import dao.custom.StudentDAO;
import dto.ReserveDTO;
import entity.Room;
import entity.Reserve;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReserveBOImpl implements ReserveBO {

    private final ReserveDAO reserveDAO =(ReserveDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVE);
    private final RoomDAO roomDAO =(RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final StudentDAO studentDAO=(StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean add(ReserveDTO reserveDTO) throws Exception {
        Student student = studentDAO.find(reserveDTO.getSID());
        Room room = roomDAO.find(reserveDTO.getRID());
        room.setRoomQty( room.getRoomQty() - 1);
        roomDAO.update(room);

        return reserveDAO.add(new Reserve(
                reserveDTO.getId(),
                student,
                room,
                reserveDTO.getDate(),
                reserveDTO.getKeyMoney()


        ));
    }

    @Override
    public List<ReserveDTO> findAll() throws Exception {


        List<ReserveDTO> reserveDTOS =new ArrayList<>();
        List<Reserve> regRoom = reserveDAO.findAll();


//        System.out.println(regRoom.get(0).getRID());
        for (Reserve reserve : regRoom) {
            reserveDTOS.add(new ReserveDTO(
                    reserve.getId(),
                    reserve.getSID().getStudentID(),
                    reserve.getRID().getRoomID(),
                    reserve.getDate(),
                    reserve.getKey_money()
            ));
        }
        return reserveDTOS;
    }

    @Override
    public ReserveDTO find() throws Exception {
        return null;
    }

    @Override
    public boolean delete(String id ) throws Exception {
        return reserveDAO.delete(id);
//
    }

    @Override
    public boolean update(ReserveDTO reserveDTO) throws Exception {
        return false;
    }

    @Override
    public String generateNewRoomId() throws SQLException, ClassNotFoundException {
        return reserveDAO.generateId();
    }

    @Override
    public Room getRoom(String id) throws Exception {
        return reserveDAO.getProgram(id);
    }

    @Override
    public Student getStudent(String id) throws Exception {
        return reserveDAO.getStudent(id);
    }

    @Override
    public List<ReserveDTO> getregRoom(String id) throws Exception {
        List<ReserveDTO> reserveDTOS =new ArrayList<>();
        List<Reserve> regProgram = reserveDAO.getRegProgram(id);
        for (Reserve reserve : regProgram) {
            reserveDTOS.add(new ReserveDTO(
                    reserve.getId(),
                    reserve.getSID().getStudentID(),
                    reserve.getRID().getRoomID(),
                    reserve.getDate(),
                    reserve.getKey_money()
            ));
        }
        return reserveDTOS;

    }
}
