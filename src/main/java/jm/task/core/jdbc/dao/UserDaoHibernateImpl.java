package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String query = "CREATE TABLE IF NOT EXISTS `mydbtest`.`user` (`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    " `name` VARCHAR(45) NULL,\n" +
                    " `lastName` VARCHAR(45) NULL,\n" +
                    " `age` INT(3) NULL,\n" +
                    " PRIMARY KEY (`id`));";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String query = "DROP TABLE IF EXISTS user";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        Session session = null;
        List<User> users = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            users = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.createSQLQuery("truncate table user").executeUpdate();

            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
