package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Dmitry", "Ivanov", (byte) 15);
        userService.saveUser("Vasya","Sidorov",(byte)17);
        userService.saveUser("Kolya","Kozlov", (byte) 16);
        userService.saveUser("Petya", "Petrov", (byte) 15);

        List users = userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
