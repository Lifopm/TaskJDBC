package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() throws SQLException {

        Connection connection = Util.getConnection();
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "polzovatel", null);

        if (!tables.next()) {

            Statement statement = Util.getStatement();
            String query = "CREATE TABLE `mydbtest`.`polzovatel` (`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    " `name` VARCHAR(45) NULL,\n" +
                    " `lastName` VARCHAR(45) NULL,\n" +
                    " `age` INT(3) NULL,\n" +
                    " PRIMARY KEY (`id`));";
            statement.execute(query);
        }
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "polzovatel", null);

        if (tables.next()) {
            Statement statement = Util.getStatement();
            statement.executeUpdate("DROP TABLE polzovatel");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Statement statement = Util.getStatement();
        statement.execute("INSERT INTO polzovatel(name, lastName, age) VALUES('" + name + "','" + lastName + "','" + age + "')");
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        Statement statement = Util.getStatement();
        statement.execute("DELETE FROM polzovatel WHERE id=" + id + "");
        System.out.println("User удален из базы данных");
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new LinkedList<>();
        Statement statement = null;
        try {
            statement = Util.getStatement();

            String query = "SELECT * FROM polzovatel";
            ResultSet resultSet = null;

            resultSet = statement.executeQuery(query);

            while (true) {
                if (!resultSet.next()) break;

                String userName = resultSet.getString("name");
                int userID = resultSet.getInt("id");
                String userLastName = resultSet.getString("lastName");
                int userAge = resultSet.getInt("age");
                User user = new User(userName, userLastName, (byte)userAge);
                users.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //User[] users = new User[];
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement = Util.getStatement();
        statement.execute("TRUNCATE polzovatel");
    }
}
