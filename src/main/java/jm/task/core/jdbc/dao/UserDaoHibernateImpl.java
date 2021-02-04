package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        //ClassMetadata temp = Util.getSessionFactory().getClassMetadata();
        //System.out.println(temp.getEntityName());

        Session session = Util.getSessionFactory().openSession();
        //Transaction transaction = session.beginTransaction();

        String query = "CREATE TABLE `mydbtest`.`user` (`id` INT NOT NULL AUTO_INCREMENT,\n" +
                " `name` VARCHAR(45) NULL,\n" +
                " `lastName` VARCHAR(45) NULL,\n" +
                " `age` INT(3) NULL,\n" +
                " PRIMARY KEY (`id`));";
        session.createSQLQuery(query).executeUpdate();
        //transaction.commit();
        session.close();


    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        String query = "DROP TABLE user";
        session.createSQLQuery(query).executeUpdate();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = Util.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        User user = new User(name, lastName, age);
        session.save(user);

        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.cancelQuery();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createCriteria(User.class).list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {

    }
}
