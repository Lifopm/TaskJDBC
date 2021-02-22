package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS `mydbtest`.`polzovatel` (`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    " `name` VARCHAR(45) NULL,\n" +
                    " `lastName` VARCHAR(45) NULL,\n" +
                    " `age` INT(3) NULL,\n" +
                    " PRIMARY KEY (`id`));";
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            String query = "DROP TABLE IF EXISTS polzovatel";
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String query = "INSERT INTO polzovatel(name, lastName, age) VALUES(?,?,?)";
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            int rows = preparedStatement.executeUpdate();
            System.out.printf("%d rows added", rows);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            String query = "DELETE FROM polzovatel WHERE id=?";
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            int rows = preparedStatement.executeUpdate();
            System.out.printf("%d rows added", rows);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();

        try {
            String query = "SELECT * FROM polzovatel";
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userName = resultSet.getString("name");
                String userLastName = resultSet.getString("lastName");
                byte userAge = resultSet.getByte("age");
                User user = new User(userName, userLastName, userAge);
                users.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try {
            String query = "TRUNCATE polzovatel";
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
