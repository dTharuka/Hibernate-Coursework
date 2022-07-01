package dao.custom.impl;

import controller.UserPrivilegesFormController;
import dao.custom.UserDAO;
import entity.Room;
import entity.Student;
import entity.SuperEntity;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);


        transaction.commit();
        session.close();
        return  true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        boolean bool = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();



        Query query = session.createQuery("DELETE FROM User WHERE UserID=:id");
        query.setParameter("id", s);

        if (query.executeUpdate() > 0) {
            bool = true;
        }

        transaction.commit();
        session.close();
        return bool;
    }

    @Override
    public User find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, s);

        transaction.commit();

        session.close();
        return user;
    }

    @Override
    public List findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query program = session.createQuery("FROM User");

        List<User> list = program.list();

        transaction.commit();

        session.close();
        return list;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
