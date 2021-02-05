package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;

import javax.persistence.Query;
import java.sql.ResultSet;
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
        ClassMetadata classMetadata = Util.getSessionFactory().getClassMetadata(User.class);
        Class res = classMetadata.getMappedClass();

        Session session = Util.getSessionFactory().openSession();
        String query = "DROP TABLE user";
        session.createSQLQuery(query).executeUpdate();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();

        User user = new User(name, lastName, age);

        session.save(user);

        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        User user = (User)session.load(User.class, id);
        session.delete(user);
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        List<User> users = session.createCriteria(User.class).list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();

        session.createSQLQuery("truncate table user").executeUpdate();
        session.close();
    }
}
