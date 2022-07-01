package dao;

import dao.custom.impl.RoomDAOImpl;
import dao.custom.impl.ReserveDAOImpl;
import dao.custom.impl.StudentDAOImpl;
import dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVE:
                return new ReserveDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT, ROOM, RESERVE , USER
    }
}
