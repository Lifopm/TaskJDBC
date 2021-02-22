package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class Main {

    //Maven JDBC
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Dmitry", "Ivanov", (byte) 15);
        userService.saveUser("Vasya","Sidorov",(byte)17);
        userService.saveUser("Kolya","Kozlov", (byte) 16);
        userService.saveUser("Petya", "Petrov", (byte) 15);

        List users = userService.getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            System.out.println("Вот такой пользователь: " + users.get(i));
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
