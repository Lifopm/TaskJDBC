package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.List;

//hibernate
public class Main {

    public static void main(String[] args) throws SQLException {


        UserService userService = new UserServiceImpl();

        userService.saveUser("D", "V", (byte)28);

        List<User> user = userService.getAllUsers();
        System.out.println(user.get(0).getName());
        //userService.createUsersTable();
//
//        userService.saveUser("Dmitry", "Ivanov", (byte) 15);
//        userService.saveUser("Vasya","Sidorov",(byte)17);
//        userService.saveUser("Kolya","Kozlov", (byte) 16);
//        userService.saveUser("Petya", "Petrov", (byte) 15);
//
//        List users = userService.getAllUsers();
//
//        userService.cleanUsersTable();
//
//        userService.dropUsersTable();
    }
}
