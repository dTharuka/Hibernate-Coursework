package dao.custom.impl;

import dao.custom.RoomDAO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public boolean add(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws Exception {
//        boolean bool = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

//
//        Query query = session.createQuery("UPDATE room SET roomQty=:roomQty,monthlyRent=:monthlyRent WHERE roomID=:roomID");
//
////=================================================================
//        query.setParameter("roomQty", entity.getRoomQty());
//        query.setParameter("monthlyRent", entity.getMonthlyRent());
//        query.setParameter("roomID", entity.getRoomID());
//
//        if (query.executeUpdate() > 0) {
//            bool = true;
//        }

        session.update(entity);


        transaction.commit();
        session.close();
//        return bool;
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        boolean bool = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM room WHERE roomID=:id");

        query.setParameter("id", s);

        if (query.executeUpdate() > 0) {
            bool = true;
        }

//        session.delete(s);

        transaction.commit();
        session.close();
        return bool;
//        return true;
    }

    @Override
    public Room find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, s);

        transaction.commit();

        session.close();
        return room;
    }

    @Override
    public List<Room> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query program = session.createQuery("FROM room");

        List<Room> list = program.list();

        transaction.commit();

        session.close();
        return list;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT roomID FROM room ORDER BY roomID DESC";
        Query sqlQuery = session.createQuery(hql);
        List<String> programids = sqlQuery.list();

        transaction.commit();
        session.close();

        for (String id : programids) {
            if (id != null) {
                int newProgramId = Integer.parseInt(id.replace("R-", "")) + 1;
                return String.format("R-%03d", newProgramId);
            }
        }
        return "R-001";
    }

}
