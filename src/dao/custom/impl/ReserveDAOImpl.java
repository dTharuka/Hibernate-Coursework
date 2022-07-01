package dao.custom.impl;

import dao.DAOFactory;
import dao.custom.RoomDAO;
import dao.custom.ReserveDAO;
import dao.custom.StudentDAO;
import entity.Room;
import entity.Reserve;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.List;

public class ReserveDAOImpl implements ReserveDAO {
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean add(Reserve entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reserve entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        boolean bool = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM Reserve WHERE id=:id");
        query.setParameter("id", s);

        if (query.executeUpdate() > 0) {
            bool = true;
        }



//        =====================================
//        Reserve s1=find(s);
//        Room s2=s1.getRID();
//        s2.setRoomQty(s2.getRoomQty()+1);

//        =====================================
        transaction.commit();
        session.close();
        return bool;
    }

    @Override
    public Reserve find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reserve reserve = session.get(Reserve.class, s);

        transaction.commit();

        session.close();
        return reserve;
    }


    @Override
    public List<Reserve> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Reserve");
        List<Reserve> list = query.list();


        transaction.commit();

        session.close();
//      System.out.println(list.get(0).getId());

        return list;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT id FROM Reserve ORDER BY id DESC LIMIT 1";
        Query sqlQuery = session.createSQLQuery(hql);
        List<String> idlist = sqlQuery.list();

        transaction.commit();
        session.close();

        for (String id : idlist) {
            if (id != null) {
                int newStudentId = Integer.parseInt(id.replace("Re-", "")) + 1;
                return String.format("Re-%03d", newStudentId);
            }
        }
        return "Re-001";
    }

    @Override
    public Room getProgram(String id) throws Exception {
        Room room = roomDAO.find(id);
        System.out.println(room);
        return room;
    }

    @Override
    public List<Reserve> getRegProgram(String sid) throws Exception {
        System.out.println(sid);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("gg");

        Query query = session.createQuery("FROM Reserve p WHERE p.sID=:id");
        query.setParameter("id", sid);
        List<Reserve> list = query.list();
        /*List<ProgramData> list=new ArrayList<>();
        ProgramData programData = session.get(ProgramData.class, sid);
        list.add(programData);*/

        transaction.commit();

        session.close();
        return list;
    }

    @Override
    public Student getStudent(String id) throws Exception {
        return studentDAO.find(id);
    }



}
