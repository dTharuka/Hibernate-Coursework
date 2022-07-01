package bo;

import bo.Impl.RoomBOImpl;
import bo.Impl.ReserveBOImpl;
import bo.Impl.StudentBOImpl;
import bo.Impl.UserBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVE:
                return new ReserveBOImpl();
            case USER:
                return new UserBoImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        STUDENT, ROOM, RESERVE ,USER
    }
}
